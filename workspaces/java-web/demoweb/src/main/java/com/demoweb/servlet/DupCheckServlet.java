package com.demoweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.demoweb.dao.MemberDao;
import com.demoweb.dto.MemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//2. 서블릿 만들기 (DupCheckServlet)
//    doGet 재정의
//    mapping : /account/dup-check
//    임시응답 : 아이디 중복 확인 연습

@WebServlet(urlPatterns = { "/account/dup-check" })
public class DupCheckServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberid");
		
		// 2. 요청 처리 ( 중복 여부 확인 - 데이터베이스 조회 )
		MemberDao dao = new MemberDao();
		int count = dao.selectMemberCountById(memberId);
		
		// 3. 결과 응답
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		if (count > 0) {
			out.print("invalid");
		} else {
			out.print("valid");
		}
	}

}











