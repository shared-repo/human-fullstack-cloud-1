package com.demoweb.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demoweb.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

	// Controller의 메서드 호출하기 전 실행
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		// System.out.println("-------------------> preHandle()");
		
		String uri = req.getRequestURI();
		// System.out.println("-------------------> " + uri);
		uri = uri.replace("/springdemoweb", "");
		if (uri.contains("/admin") ||
			uri.contains("/mail") ||
			uri.contains("/library") ||
			uri.contains("/board/write") ||
			uri.contains("/board/delete")){
			
			MemberDto member = (MemberDto)req.getSession().getAttribute("loginuser");
			if (member == null) { 
				resp.sendRedirect("/springdemoweb2/account/login?returnUrl=" + uri);
				return false; // 요청 처리 중단
			}
			if (uri.contains("/admin") && !member.getUserType().equals("admin")) {
				resp.sendRedirect("/springdemoweb2/account/login?returnUrl=" + uri);
				return false; // 요청 처리 중단
			}			
		}
		
		return true; // 요청 처리 계속
	}

	// Controller의 메서드 호출한 후 실행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		// System.out.println("-------------------> postHandle()");
	}
	// 요청 처리 프로세스가 완료된 후 실행 ( view 처리가 완료된 후 )
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		// System.out.println("-------------------> afterCompletion()");
	}

}
