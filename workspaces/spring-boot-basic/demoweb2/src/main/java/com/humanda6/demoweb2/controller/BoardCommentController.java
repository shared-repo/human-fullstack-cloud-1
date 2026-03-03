package com.humanda6.demoweb2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humanda6.demoweb2.dto.BoardCommentDto;
import com.humanda6.demoweb2.service.BoardCommentService;

@Controller
@RequestMapping(path = { "/board" })
public class BoardCommentController {
	
	private BoardCommentService boardCommentService;
	public BoardCommentController(BoardCommentService boardCommentService) {
		this.boardCommentService = boardCommentService;
	}
	
	@GetMapping(path = { "/comment-list" })
	public String showCommentList(@RequestParam("boardNo") int boardNo, Model model) {
		
		List<BoardCommentDto> comments = boardCommentService.findBoardCommentByBoardNo(boardNo);
		
		// View에서 읽을 수 있도록 데이터 저장
		model.addAttribute("comments", comments);
		
		return "board/comment-list"; //  
	}
	
	@PostMapping(path = { "/write-comment" })
	@ResponseBody // 메서드의 반환 값을 view의 이름으로 사용하지 않고 있는 그대로 응답하는 설정
	public String writeComment(BoardCommentDto commentDto, @RequestParam("pageNo")int pageNo) {
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )
		System.out.println(commentDto);
		// 2. 요청 처리
		boardCommentService.writeComment(commentDto); // commentDto에 자동 증가된 commentNo가 저장됩니다.
		// 최상위 댓글의 글번호를 그룹번호로 저장
		boardCommentService.updateGroupNo(commentDto.getCommentNo(), commentDto.getCommentNo());		
		// 3. View에서 읽을 수 있도록 데이터 저장		
		// 4. View 또는 다른 컨터롤러로 이동
		// return String.format("redirect:detail?boardNo=%d&pageNo=%d", commentDto.getBoardNo(), pageNo);
		return "success";
	}
	
	@GetMapping(path = { "/delete-comment" })
	@ResponseBody // 반환 값은 view 이름이 아니고 응답 컨텐츠 입니다.
	public String deleteComment(@RequestParam(name="commentNo", defaultValue = "-1") int commentNo) {
		
		//1. 요청 데이터 읽기 (전달인자로 대체)
		if (commentNo == -1) {
			return "fail";	// "fail" 문자열을 응답 (@ResponseBody 때문에)
		}
		
		// 2. 데이터 처리
		boardCommentService.deleteComment(commentNo);		
		
		return "success"; // "success" 문자열을 응답 (@ResponseBody 때문에)
	}
	
	@PostMapping(path = { "/update-comment" })
	@ResponseBody
	public String updateComment(BoardCommentDto comment) {
		
		boardCommentService.updateComment(comment);
		
		return "success";
	}
	
	@PostMapping(path = { "/write-recomment" })
	@ResponseBody
	public String writeReComment(BoardCommentDto commentDto) {
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )
		// 2. 요청 처리
		boardCommentService.writeReComment(commentDto); // commentDto에 자동 증가된 commentNo가 저장됩니다.

		// 3. View에서 읽을 수 있도록 데이터 저장		
		// 4. View 또는 다른 컨터롤러로 이동
		// return String.format("redirect:detail?boardNo=%d&pageNo=%d", commentDto.getBoardNo(), pageNo);
		return "success";
	}
	
	
	
	
	
	
	

}
