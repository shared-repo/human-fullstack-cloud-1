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
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/account/login" })
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req	.getRequestDispatcher("/WEB-INF/views/account/login.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		passwd = Util.getHashedString(passwd, "SHA-256");
		
		// 2. 요청 처리 (로그인)
		// 2-1. 로그인 가능 여부 확인
		// 		a. MemberDao 객체 만들기
		MemberDao dao = new MemberDao();
		// 		b. a에서 만든 객체의 selectMemberByIdAndPasswd 메서드 호출
		MemberDto member = dao.selectMemberByIdAndPasswd(memberId, passwd);
		//         (전달인자 : memberId, passwd)
		//         반환값을 MemberDto 타입 변수 member에 저장
		//      
		boolean success = true;
		//      c. 아래 조건식을 b에서 만든 member 변수가 null인지 확인하는 것으로 변경 
		if (member != null) { // 성공
			// 2-2. 로그인 처리 : session 객체에 데이터 저장
			HttpSession session = req.getSession(); // 세션 객체 준비
			session.setAttribute("loginuser", member);
		} else { // 실패
			success = false;
		}
		
		if (success) {
			// 다른 작업 영역으로 이동할 때에는 redirect로 이동합니다.
			resp.sendRedirect("/demoweb/home"); // home으로 이동
		} else {
			// 3. JSP에서 읽을 수 있도록 request 객체에 데이터 저장
			req.setAttribute("loginfail", "fail");
			
			// 4. JSP로 forward
			req	.getRequestDispatcher("/WEB-INF/views/account/login.jsp")
				.forward(req, resp);
			
		}

		

		
		
	}

}







