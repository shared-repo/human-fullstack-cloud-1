package com.humanda6.demoweb2.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.humanda6.demoweb2.common.Util;
import com.humanda6.demoweb2.dto.BoardAttachDto;
import com.humanda6.demoweb2.dto.BoardDto;
import com.humanda6.demoweb2.dto.PagerDto;
import com.humanda6.demoweb2.service.BoardService;
import com.humanda6.demoweb2.service.BoardServiceImpl;
import com.humanda6.demoweb2.view.DownloadView;

@Controller // flask의 Blueprint 역할
public class BoardController {
	
	private final int PAGE_SIZE = 3; 	// 한 페이지에 표시되는 데이터 개수
	private final int PAGER_SIZE = 3;	// 한 번에 표시할 페이지 번호 개수
	private final String LINK_URL = "list"; // 페이지 번호를 클릭했을 때 이동할 페이지 경로
	
	private final String UPLOAD_PATH = "D:\\instructor-och\\human-da6\\workspace\\eclipse-workspace\\upload-files";

	
	@Autowired // BoardService 인터페이스를 구현한 클래스를 자동으로 주입
	@Qualifier("boardService")
	BoardService boardService;
	

//	@GetMapping(path = { "/board/list" })
//	// @ResponseBody // return 하는 문자열을 그대로 클라이언트(브라우저)에 전송
//	public String list(Model model) {
//		
//		List<BoardDto> boards = boardService.findAllBoard();
//		model.addAttribute("boards", boards);
//		
//		return "board/list"; // "templates/" + "board/list" + ".html"
//	}
	
	@GetMapping(path = { "/board/list" })
	public String listWithPaging(@RequestParam(value="pageNo", defaultValue="1") int pageNo, 
								 Model model) {
		
		List<BoardDto> boards = boardService.findBoardByPage(pageNo, PAGE_SIZE);
		int boardCount = boardService.findBoardCount(); // 페이지 갯수를 계산하기 위한 데이터 조회
		
		// 페이저 (페이지 번호 목록) 표시를 위한 데이터 처리
		int pagerBlock = (pageNo - 1) / PAGER_SIZE;
		int start = (pagerBlock * PAGER_SIZE) + 1;
		int end = start + PAGER_SIZE;
		
		PagerDto pager = new PagerDto();
		pager.setPageNo(pageNo);
		pager.setPageSize(PAGE_SIZE);
		pager.setPagerSize(PAGER_SIZE);
		pager.setDataCnt(boardCount);
		pager.setPageStart(start);
		pager.setPageEnd(end);
		pager.setLinkUrl(LINK_URL);
		int pageCnt = (boardCount / PAGE_SIZE) + ((boardCount % PAGE_SIZE) > 0 ? 1 : 0);
		pager.setPageCnt(pageCnt);
		
		model.addAttribute("boards", boards);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "board/list"; // "templates/" + "board/list" + ".html"
	}
	
	@GetMapping(path = { "/board/write" })
	public String showWriteForm() {
		
		return "board/write"; // "templates/" + "board/write" + ".html"
	}
	
//	@PostMapping(path = { "/board/write" })
//	public String doWrite(BoardDto board) {
//		
//		// System.out.println(board);
//		int newBoardNo = boardService.writeBoard(board);
//		System.out.println(newBoardNo);
//		
//		return "redirect:/board/list";
//	}
	
//	@PostMapping(path = { "/board/write" })
//	public String writeBoard(BoardDto board, 
//							 //@RequestParam("attachment") MultipartFile[] attachments) {
//							 @RequestParam("attachment") MultipartFile attachment) {
	
	@PostMapping(path = { "/board/write" })
	public String writeBoard(BoardDto board, MultipartHttpServletRequest req) {
		// 1. 요청 데이터 읽기 (전달인자로 대체)
		MultipartFile attach = req.getFile("attachment");

		if (attach != null) { //내용이 있는 경우
			// 2. 데이터 처리			
			String fileName = attach.getOriginalFilename(); //파일 이름 가져오기
			if (fileName != null && fileName.length() > 0) {
				String uniqueFileName = Util.makeUniqueFileName(fileName);
				
				try {				
					attach.transferTo(new File(UPLOAD_PATH, uniqueFileName)); //파일 저장
					
					// 첨부파일 정보를 객체에 저장
					ArrayList<BoardAttachDto> attachments = new ArrayList<>(); // 첨부파일 정보를 저장하는 DTO 객체
					BoardAttachDto attachment = new BoardAttachDto();
					attachment.setUserFileName(fileName);
					attachment.setSavedFileName(uniqueFileName);
					attachments.add(attachment);
					board.setAttachments(attachments);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		boardService.writeBoard(board);
		// 3. View에서 읽을 수 있도록 데이터 저장
		// 4. View 또는 Controller로 이동
		return "redirect:list";
	}
	
	@GetMapping(path = { "/board/detail" })
	public String detail(@RequestParam(value = "boardNo", defaultValue = "-1") int boardNo, 
						 @RequestParam(value = "pageNo", defaultValue = "-1") int pageNo,
						 Model model) {
		
		if (boardNo == -1 || pageNo == -1) {
			return "redirect:/board/list";
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		if (board == null) {
			return "redirect:/board/list";
		}
		
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		
		return "board/detail";
	}
	
	@GetMapping(path = { "/board/delete" })
	public String delete(@RequestParam(value = "boardNo", defaultValue="-1") int boardNo,
						 @RequestParam(value = "pageNo", defaultValue="-1") int pageNo) {
		
		if (boardNo == -1 || pageNo == -1) {
			return "redirect:/board/list";
		}

		boardService.deleteBoardByBoardNo(boardNo);
			
		return "redirect:list?pageNo=" + pageNo;
	}
	
	@GetMapping(path = { "/board/edit" })
	public String showEditForm(	@RequestParam(value = "boardNo", defaultValue="-1") int boardNo,
						 		@RequestParam(value = "pageNo", defaultValue="-1") int pageNo,
						 		Model model) {
		
		if (boardNo == -1 || pageNo == -1) {
			return "redirect:/board/list";
		}

		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		if (board == null) {
			return "redirect:/board/list";
		}
		
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
			
		return "board/edit";
	}
	
	@PostMapping(path = { "/board/update" })
	public String doUpdate(BoardDto board, @RequestParam(value = "pageNo", defaultValue = "-1") int pageNo) {
		//1. 데이터 읽기 // --> 메서드의 전달인자를 통해 자동으로 읽기
		
		if (pageNo == -1) {
			return "redirect:list";
		}
		
		//2. 글쓰기 처리 ( 데이터베이스에 데이터 저장 ) // Service에 요청
		boardService.updateBoard(board);
		
		//3. 응답컨텐츠 생산 // --> 템플릿에 요청	
		return "redirect:/board/detail?boardNo=" + board.getBoardNo() + "&pageNo" + pageNo;
	}
	
	@GetMapping(path = { "/board/download" })
	public View doDownload(@RequestParam(value="attachNo", defaultValue="-1") int attachNo, Model model) {
		
		if (attachNo == -1) {
			return new RedirectView("/board/list");
		}
		
		BoardAttachDto attachment = boardService.findBoardAttachByAttachNo(attachNo);
		if (attachment == null) {
			return new RedirectView("/board/list");
		}
		
		model.addAttribute("attachment", attachment);
		model.addAttribute("filepath", new File(UPLOAD_PATH, attachment.getSavedFileName()).getAbsolutePath());
		View downloadView = new DownloadView();
		
		return downloadView;
	}


}
