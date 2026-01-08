package com.demoweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/account/logout" })
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		// 2. 요청 처리 (로그아웃)
		HttpSession session = req.getSession();
		session.removeAttribute("loginuser"); // 로그아웃 : session에서 해당 데이터 삭제
		// session.invalidate(); // 로그아웃 : session을 삭제 --> session에서 모든 데이터 삭제 
		
		// 3-1. JSP에서 읽을 수 있도록 request에 데이터 저장
		// 3-2. JSP로 forward
		// or
		// 4. 다른 서블릿으로 redirect
		resp.sendRedirect("login");
		
	}

}
