package com.demoweb.servlet;

import java.io.IOException;

import com.demoweb.common.Util;
import com.demoweb.dao.MemberDao;
import com.demoweb.dto.MemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/account/register" })
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req	.getRequestDispatcher("/WEB-INF/views/account/register.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 데이터 읽기 (memberId, passwd, email)
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		passwd = Util.getHashedString(passwd, "SHA-256");
		String email = req.getParameter("email");
		
		// 2. MemberDto 객체 만들기 + 1에서 읽은 데이터를 저장 (set 계열 메서드 사용)
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		member.setEmail(email);
		
		// 3. MemberDao 객체 만들기
		MemberDao dao = new MemberDao();
		
		// 4. MemberDao의 insertMember 메서드 호출 (전달인자 2에서 만든 MemberDto)
		dao.insertMember(member);
		
		// 5. 로그인 서블릿으로 redirect 이동
		resp.sendRedirect("login");
	}

}













