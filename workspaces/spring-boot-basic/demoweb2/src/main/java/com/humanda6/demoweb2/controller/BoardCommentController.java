package com.humanda6.demoweb2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humanda6.demoweb2.dto.BoardCommentDto;

@Controller
@RequestMapping(path = { "/board" })
public class BoardCommentController {
	
	@PostMapping(path = { "/write-comment" })
	@ResponseBody // 메서드의 반환 값을 view의 이름으로 사용하지 않고 있는 그대로 응답하는 설정
	public String writeComment(BoardCommentDto commentDto, @RequestParam("pageNo")int pageNo) {
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )
		System.out.println(commentDto);
		// 2. 요청 처리
		// boardCommentService.writeComment(commentDto); // commentDto에 자동 증가된 commentNo가 저장됩니다.
		// 최상위 댓글의 글번호를 그룹번호로 저장
		//  boardCommentService.updateGroupNo(commentDto.getCommentNo(), commentDto.getCommentNo());		
		// 3. View에서 읽을 수 있도록 데이터 저장		
		// 4. View 또는 다른 컨터롤러로 이동
		// return String.format("redirect:detail?boardNo=%d&pageNo=%d", commentDto.getBoardNo(), pageNo);
		return "success";
	}
	
	
	
	
	
	
	
	
	

}
