package com.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demoweb.common.Util;
import com.demoweb.dao.MemberDao;
import com.demoweb.dto.MemberDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	
	@Autowired // IoC Container가 자동으로 MemberDao 호환 타입 객체를 주입
	// @Qualifier("dataSourceMemberDao") 	// dataSourceMemberDao로 등록된 객체로 한정
	// @Qualifier("jdbcTemplateMemberDao") 	// jdbcTemplateMemberDao로 등록된 객체로 한정
	@Qualifier("mybatisMemberDao") 			// mybatisMemberDao로 등록된 객체로 한정
	private MemberDao dao;

	@GetMapping(path = { "/account/login" })
	public String loginView(
			@RequestParam(value="returnUrl", defaultValue = "/home") String returnUrl,
			Model model) {
		
		model.addAttribute("returnUrl", returnUrl);
		
		return "account/login"; // "/WEB-INF/views/" + account/login + ".jsp"
	}
	
	@PostMapping(path = { "/account/login" })
	public String login(
			@RequestParam("memberId") String memberId,
			@RequestParam("passwd") String passwd,
			@RequestParam("returnUrl") String returnUrl,
			HttpSession session,
			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 통로
		
		// 1. 요청 데이터 읽기 - 메서드의 전달인자에 자동으로 저장
		passwd = Util.getHashedString(passwd, "SHA-256");
		
		// 2. 요청 처리 (로그인)
		// 2-1. 로그인 가능 여부 확인
		// 		a. MemberDao 객체 만들기
		//         의존 주입으로 처리
		// 		b. a에서 만든 객체의 selectMemberByIdAndPasswd 메서드 호출
		MemberDto member = dao.selectMemberByIdAndPasswd(memberId, passwd);
		//         (전달인자 : memberId, passwd)
		//         반환값을 MemberDto 타입 변수 member에 저장
		//      
		boolean success = true;
		//      c. 아래 조건식을 b에서 만든 member 변수가 null인지 확인하는 것으로 변경 
		if (member != null) { // 성공
			// 2-2. 로그인 처리 : session 객체에 데이터 저장
			// 세션 객체 준비 - 전달인자에 자동으로 저장
			session.setAttribute("loginuser", member);
		} else { // 실패
			success = false;
		}
		
		if (success) {
			// 다른 작업 영역으로 이동할 때에는 redirect로 이동합니다.
			// return "redirect:/home"; // home으로 이동
			return "redirect:" + returnUrl;
		} else {
			// 3. View에서 읽을 수 있도록 Model 타입 객체에 데이터 저장
			model.addAttribute("loginfail", "fail");
			
			// 4. View로 이동
			return "account/login";
		}
	}
	
	@GetMapping(path = { "/account/register" })
	public String registerView() {
		
		return "account/register";
	}

	@PostMapping(path = { "account/register" })
	public String register(MemberDto member) {
		
		// 1. 요청 데이터 읽기 (memberId, passwd, email)
		// 2. MemberDto 객체 만들기 + 1에서 읽은 데이터를 저장 (set 계열 메서드 사용)
		// 위 두 개의 작업은 MemberDto 전달인자로 자동 처리
		String hashedPasswd = Util.getHashedString(member.getPasswd(), "SHA-256");
		member.setPasswd(hashedPasswd);
		
		// 3. MemberDao 객체 만들기
		//    의존 객체 주입으로 처리
		
		// 4. MemberDao의 insertMember 메서드 호출 (전달인자 2에서 만든 MemberDto)
		dao.insertMember(member);
		
		// 5. 로그인 서블릿으로 redirect 이동
		// return "redirect:/account/login"; // 절대 경로
		return "redirect:login"; // 상대 경로
	}
	
	@GetMapping(path = { "/account/logout" })
	public String logout(HttpSession session) {

		session.removeAttribute("loginuser"); // 로그아웃 : session에서 해당 데이터 삭제
		// session.invalidate(); // 로그아웃 : session을 삭제 --> session에서 모든 데이터 삭제 
		
		// return "redirect:login";
		return "redirect:/home";
	}
	
	// 아이디 중복검사
	// 1. 메서드 만들기
	// 2. @GetMapping : account/dup-check
	// 3. 중복 검사 구현
	//    DemoController의 9번 작업
	//    DupCheckServlet doGet
	@GetMapping(path = { "/account/dup-check" }, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String checkDup(
			@RequestParam("memberid") String memberId) {
		
		// 1. 요청 데이터 읽기 - 전달인자를 통해 직접 수신
		
		// 2. 요청 처리 ( 중복 여부 확인 - 데이터베이스 조회 )
		int count = dao.selectMemberCountById(memberId);
		
		// 3. 결과 응답		
//		if (count > 0) {
//		 	return "invalid";
//		} else {
//			return "valid";
//		}
		return count > 0 ? "invalid" : "valid";
	}
	
	
}













