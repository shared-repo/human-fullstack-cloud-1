package com.exampleweb.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 1. HttpServlet 상속
// 2. doGet, doPost 적절하게 재정의
// 3. 컨테이너에 등록 ( web.xml or @WebServlet )
@WebServlet(urlPatterns = { "/dogame" } )
public class GameServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 데이터 읽기
		String userStr = req.getParameter("user");
		int you = Integer.parseInt(userStr);
		
		// 2. 요청 처리
		int com = (int) (Math.random() * 3) + 1; // 1, 2, 3 : 컴퓨터 번호

		String result = "";
		if ((you == 1 && com == 3) || (you == 2 && com == 1) || (you == 3 && com == 2)) {
			result = "이겼습니다.";
		} else if (you == com) {
			result = "비겼습니다.";
		} else {
			result = "졌습니다.";
		}
		
		// 3. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		req.setAttribute("result", result);
		
		// 4. 응답 컨텐츠 생산 ( JSP에서 처리하도록 forward )
		RequestDispatcher rd = req.getRequestDispatcher("05.game-result.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		// 2. 요청 처리
		// 3. JSP에서 읽을 수 있도록 request에 데이터 저장
		// 4. JSP로 이동
		req	.getRequestDispatcher("05.game.jsp")
			.forward(req, resp);
		
	}
	
}









