package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/write" })
public class BoardWriteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req	.getRequestDispatcher("/WEB-INF/views/board/write.jsp")
			.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기 (title, writer, content)
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		// 2. 요청 처리 (데이터베이스에 데이터 저장)
		//    1에서 읽은 데이터만 DTO에 저장해서 BoardDao.insertBoard 호출
		BoardDto board = new BoardDto();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		BoardDao dao = new BoardDao();
		dao.insertBoard(board);
		
		// 3. 목록으로 이동 ( list로 redirect 이동 )
		resp.sendRedirect("list");
		
	}

}

// BoardDto 클래스 만들기 ( MemberDto 참고 )
// BoardDao 클래스를 만들고 insertBoard 메서드 만들기 ( MemberDao의 insertMember 참고 )








