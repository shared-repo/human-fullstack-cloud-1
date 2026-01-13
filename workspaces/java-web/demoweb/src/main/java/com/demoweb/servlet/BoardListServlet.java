package com.demoweb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/list" })
public class BoardListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청 데이터 읽기
		// 2. 요청 처리 ( 데이터 조회 )
		BoardDao dao = new BoardDao();
		ArrayList<BoardDto> boards = dao.selectBoardList();
		
		// 3. JSP에서 사용하도록 request에 데이터 저장
		req.setAttribute("boards", boards);
		
		// 4. JSP로 forward 이동
		req	.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
			.forward(req, resp);

	}

}










