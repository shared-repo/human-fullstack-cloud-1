package com.humanda6.demoweb2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.humanda6.demoweb2.dto.MemberDto;
import com.humanda6.demoweb2.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller // Flask의 Blueprint
public class AuthController {
	
	@Autowired // 스프링 프레임워크가 관리하는 AuthService 객체를 주입하세요
	private AuthService authService;
	
	@GetMapping(path = { "/auth/signup" }) // Flask의 @...route
	public String showSignupForm() {
		
		return "auth/signup";	// --> "templates/" + "auth/signup" + ".html"
	}
	
	@PostMapping(path = { "/auth/signup" }) // Flask의 @...route
	public String doSignup(MemberDto member) {
		
		//1. 요청 데이터 읽기 ( 메서드의 전달인자로 수신 )
		System.out.println(member); // python의 print 함수와 같은 기능 수행
		
		//2. 데이터 처리 ( 서비스 객체에 요청 )
		// AuthService authService = new AuthService(); // 파이썬 구현 --> authService = AuthService()
		authService.signup(member);
		
		//3. 응답 컨텐츠 만들기
		
		return "redirect:/auth/signin";
	}
	
	
	@GetMapping(path = { "/auth/signin" }) // Flask의 @...route
	public String showSigninForm() {
		
		return "auth/signin";
	}
	
	@PostMapping(path = { "/auth/signin" }) // Flask의 @...route
	public String doLogin(@RequestParam("email") String email,
						  @RequestParam("passwd") String passwd,
						  HttpSession session) { // 필요한 것을 전달인자에 등록하면 스프링이 자동으로 채워줍니다.
		
		// 1. 데이터 읽기
		//	  @RequestParam으로 자동 읽기
		
		// 2. 데이터 처리
		//    && --> 파이썬의 and
		// AuthService authService = new AuthService();
		MemberDto member = authService.findMemberByEmailAndPasswd(email, passwd);
		
		if (member != null) {
			// session.setAttribute("loginuser", email); // 로그인 : 세션에 데이터 저장
			session.setAttribute("loginuser", member); // 로그인 : 세션에 데이터 저장
			// main으로 redirect 이동
			return "redirect:/";
		} else {			
			return "auth/signin";
		}
		
	}
	
	@GetMapping(path = { "/auth/signout" }) // Flask의 @...route
	public String doSignout(HttpSession session) { // 필요한 것을 전달인자에 등록하면 스프링이 자동으로 채워줍니다.

		session.removeAttribute("loginuser"); // 세션에서 한 개의 값 제거 --> Flask의 del
		// session.invalidate(); // 세션에 저장된 모든 값 제거 --> Flask의 clear

		return "redirect:/";// main으로 이동
	}

}













