package com.demoweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.demoweb.common.Util;
import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;
import com.demoweb.ui.ThePager;
import com.demoweb.view.DownloadView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	private BoardDao boardDao;
	public BoardController(BoardDao boardDao) { // 생성자의 전달인자는 자동으로 의존 객체 주입
		this.boardDao = boardDao;
	}
	
//	@GetMapping(path = { "/board/list" })
//	public String list(
//			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 도구		
//
//		ArrayList<BoardDto> boards = boardDao.selectBoardList();
//		model.addAttribute("boards", boards); // 실제로는 request 객체에 저장
//		
//		return "board/list";
//	}
	
	@GetMapping(path = { "/board/list" })
	public String list(
			@RequestParam(value="pageNo", defaultValue = "1") int pageNo,
			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 도구
		
		int pageSize = 3;	// 한 페이지에 표시하는 데이터 갯수
		int pagerSize = 3;	// 한 번에 표시되는 페이지 번호 갯수
		int start = (pageNo - 1) * pageSize;
		
		int dataCount = boardDao.selectBoardCount(); // 전체 데이터 갯수 조회
		
		// 페이지번호, 이전, 다음, 처음, 마지막 등의 링크를 만드는 클래스의 인스턴스 만들기
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, "list", null);

		ArrayList<BoardDto> boards = boardDao.selectBoardListByPage(start, pageSize);
		model.addAttribute("boards", boards); // 실제로는 request 객체에 저장
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "board/list";
	}
	
	@GetMapping(path = { "/board/write" })
	public String writeForm() {	
		
		return "board/write";
	}
	
//	@PostMapping(path = { "/board/write" })
//	public String write(BoardDto board) {
//		
//		boardDao.insertBoard(board);
//		
//		return "redirect:list";
//	}
	
	@PostMapping(path = { "/board/write" })
	public String write(
			BoardDto board,
			@RequestParam("attach") MultipartFile attach, // <input type="file" name="attach" 데이터 수신
			MultipartHttpServletRequest req) {
		
//		MultipartFile attach2 = req.getFile("attach"); //<input type="file" name="attach" 데이터 수신
//		if (attach2 != null) {
//			System.out.println("-----------------> " + attach2.getOriginalFilename());
//		}
		
		if (attach != null && attach.getOriginalFilename().length() > 0) {
			// System.out.println("-----------------> " + attach.getOriginalFilename());
			
			BoardAttachDto attachment = new BoardAttachDto();
			ArrayList<BoardAttachDto> attachments = new ArrayList<>();
			try {
				String dir = req.getServletContext().getRealPath("/board-attach");
				String userFileName = attach.getOriginalFilename();
				String savedFileName = Util.makeUniqueFileName(userFileName);			
				attach.transferTo(new File(dir, savedFileName)); // 파일 저장
				
				attachment.setUserFileName(userFileName);
				attachment.setSavedFileName(savedFileName);
				attachments.add(attachment);
				board.setAttachments(attachments);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		// boardno == ?
		// System.out.println("-------------------------> " + board.getBoardNo());
		
		boardDao.insertBoard(board); // boardno 결정 ( auto_increment이기 때문에 )
		
		// boardno == !
		//System.out.println("-------------------------> " + board.getBoardNo());
		
		if (board.getAttachments() != null && board.getAttachments().size() > 0) {
			for (BoardAttachDto a : board.getAttachments()) {
				a.setBoardNo(board.getBoardNo()); // board를 insert한 이후에 글번호 사용 가능
				boardDao.insertBoardAttach(a);
			}
		}
		
		return "redirect:list";
	}
	
	@GetMapping(path = { "/board/detail" })
	public String detail(
			HttpSession session,
			@RequestParam("boardNo") int boardNo,
			@RequestParam("pageNo") int pageNo,
			Model model) {
		// 1. 요청 데이터 읽기 ( 전달인자에서 직접 수신 )
		
//		// 2-1. 요청 처리 ( 데이터 조회 )
//		BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
//		if (board == null) {
//			return "redirect:list"; 
//		}		
//		// 2-2
//		ArrayList<BoardAttachDto> attachments = 
//				boardDao.selectBoardAttachmentsByBoardNo(boardNo);
//		board.setAttachments(attachments);
		
		BoardDto board = boardDao.selectBoardByBoardNo2(boardNo);
		if (board == null) {
			return "redirect:list"; 
		}
		
		// 2-3. 요청 처리 ( 조회수 증가 - 데이터베이스 데이터 수정 )
		ArrayList<Integer> readList = // 세션에 저장된 읽은 글 목록 조회 
				(ArrayList<Integer>)session.getAttribute("readlist");		
		if (readList == null) {
			readList = new ArrayList<>();
		}
		if (!readList.contains(boardNo)) { // 현재 글 번호가 읽은 글 목록에 없다면
			boardDao.updateBoardReadCount(boardNo); // 조회수 증가
			board.setReadCount(board.getReadCount() + 1);
			readList.add(boardNo); // 읽은 글 목록에 현재 글 번호 추가
		}
		session.setAttribute("readlist", readList);
		
		// 3. View에서 읽을 수 있도로 데이터 전달 (Model 타입 전달인자에 저장)
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		// 4. View로 이동
		return "board/detail";
	}
	
	@GetMapping(path = { "/board/detail/{boardNo}" }) // { boardNo } : 경로의 일부이면서 데이터
	public String detail2(
			HttpSession session,
			@PathVariable("boardNo") int boardNo, // @PathVariable : 경로에 포함된 데이터 읽기
			@RequestParam("pageNo") int pageNo,
			Model model) {
		// 1. 요청 데이터 읽기 ( 전달인자에서 직접 수신 )
		// 2-1. 요청 처리 ( 데이터 조회 )
		BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
		if (board == null) {
			return "redirect:/board/list"; 
		}
		// 2-2
		ArrayList<BoardAttachDto> attachments = 
				boardDao.selectBoardAttachmentsByBoardNo(boardNo);
		board.setAttachments(attachments);
		// 2-3. 요청 처리 ( 조회수 증가 - 데이터베이스 데이터 수정 )
		ArrayList<Integer> readList = // 세션에 저장된 읽은 글 목록 조회 
				(ArrayList<Integer>)session.getAttribute("readlist");
		if (readList == null) {
			readList = new ArrayList<>();
		}
		if (!readList.contains(boardNo)) { // 현재 글 번호가 읽은 글 목록에 없다면
			boardDao.updateBoardReadCount(boardNo); // 조회수 증가
			board.setReadCount(board.getReadCount() + 1);
			readList.add(boardNo); // 읽은 글 목록에 현재 글 번호 추가
		}
		session.setAttribute("readlist", readList);
		
		// 3. View에서 읽을 수 있도록 데이터 전달 (Model 타입 전달인자에 저장)
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		// 4. View로 이동
		return "board/detail";
	}
	
	@GetMapping(path = { "/board/delete/{boardNo}" })
	public String delete(
			HttpSession session,
			@PathVariable("boardNo") int boardNo,
			@RequestParam("pageNo") int pageNo) {
		
		BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
		MemberDto member = (MemberDto)session.getAttribute("loginuser");
		if (board == null || 
			!member.getMemberId().equals(board.getWriter())) {
			return "redirect:/board/list";
		}
		
		boardDao.deleteBoard(boardNo);
		
		return "redirect:/board/list?pageNo=" + pageNo;
	}
	
	@GetMapping(path = { "/board/download/{attachNo}" })
	public View download(
			@PathVariable("attachNo") int attachNo,
			Model model) {
		// System.out.println("-------------------------> " + attachNo);
		// 1. 요청 데이터 읽기 ( 전달인자에서 직접 수신 )
		// 2. 요청 처리 ( 데이터 조회 )		
		BoardAttachDto attachment = boardDao.selectBoardAttachByAttachNo(attachNo);
		if (attachment == null) {
			// return "redirect:/board/list"; // 오류 : 아래쪽 반환 값의 타입과 불일치
			return new RedirectView("/board/list");
		}
		
		// 3. View에서 읽을 수 있도록 Model 타입 전달인자에 데이터 저장
		model.addAttribute("attachment", attachment);
		
		// 4. 다운로드 처리 -> 응답 컨텐츠가 파일
		// return "download"; // jsp로 응답 컨텐츠 생산 -> HTML 응답
		// return "some message" + @ResponseBody // 텍스트 응답
		return new DownloadView();
	}
	
	@GetMapping(path = { "/board/edit/{boardNo}" })
	public String editForm(
			@PathVariable("boardNo") int boardNo,
			@RequestParam("pageNo") int pageNo,
			Model model) {
		
		BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
		if (board == null) {
			return "redirect:/board/list";
		}
		ArrayList<BoardAttachDto> attachments = 
				boardDao.selectBoardAttachmentsByBoardNo(boardNo);
		board.setAttachments(attachments);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		
		return "board/edit";
	}
	
	@PostMapping(path = { "/board/edit" })
	public String edit(
			BoardDto board,
			@RequestParam("attach") MultipartFile attach,
			@RequestParam("pageNo") int pageNo,
			HttpServletRequest req) {
		
		if (attach != null && attach.getOriginalFilename().length() > 0) {
			BoardAttachDto attachment = new BoardAttachDto();
			ArrayList<BoardAttachDto> attachments = new ArrayList<>();
			try {
				String dir = req.getServletContext().getRealPath("/board-attach");
				String userFileName = attach.getOriginalFilename();
				String savedFileName = Util.makeUniqueFileName(userFileName);			
				attach.transferTo(new File(dir, savedFileName)); // 파일 저장
				
				attachment.setBoardNo(board.getBoardNo());
				attachment.setUserFileName(userFileName);
				attachment.setSavedFileName(savedFileName);
				attachments.add(attachment);			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			board.setAttachments(attachments);
		}
		
		try {		
			boardDao.updateBoard(board);
			if (board.getAttachments() != null && board.getAttachments().size() > 0) {
				for (BoardAttachDto a : board.getAttachments()) {
					a.setBoardNo(board.getBoardNo()); // board를 insert한 이후에 글번호 사용 가능
					boardDao.insertBoardAttach(a);
				}
			}	
		} catch (Exception ex) {
			System.out.println("글수정 실패");
			return String.format("redirect:edit/%d?pageNo=%d", board.getBoardNo(), pageNo);
		}
		
		return String.format(
				"redirect:detail?boardNo=%d&pageNo=%d", 
				board.getBoardNo(), pageNo);
	}
	
	@GetMapping(path = { "/board/delete-attach/{attachNo}" })
	@ResponseBody // return 값을 jsp 파일로 해석하지 않고 그대로 응답으로 출력
	public String deleteAttach(
			@PathVariable("attachNo") int attachNo) {
		
		try {
			boardDao.deleteBoardAttach(attachNo);
		} catch (Exception ex) {
			return "fail";
		}
		
		return "success";
	}
}











