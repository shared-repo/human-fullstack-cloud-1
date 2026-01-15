package com.demoweb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardDto;

@Controller
public class BoardController {

	private BoardDao boardDao;
	public BoardController(BoardDao boardDao) { // 생성자의 전달인자는 자동으로 의존 객체 주입
		this.boardDao = boardDao;
	}
	
	@GetMapping(path = { "/board/list" })
	public String list(
			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 도구		

		ArrayList<BoardDto> boards = boardDao.selectBoardList();
		model.addAttribute("boards", boards); // 실제로는 request 객체에 저장
		
		return "board/list";
	}
	
	@GetMapping(path = { "/board/write" })
	public String writeForm() {	
		
		return "board/write";
	}
	
	// 1. 메서드 만들기
	// 2. PostMapping : /board/write
	// 3. 내용 구현
	//    - 요청 데이터 읽기
	//    - 데이터베이스에 저장 ( boardDao.insertBoard 사용 )
	//    - list로 redirect 이동
	
	// 참고 : AuthController의 register, demoweb의 BoardWriteServlet
	@PostMapping(path = { "/board/write" })
	public String write(BoardDto board) {
		
		boardDao.insertBoard(board);
		
		return "redirect:list";
	}
}








