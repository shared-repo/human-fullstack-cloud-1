package com.demoweb.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class DemoWebListener implements ServletContextListener, HttpSessionListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 1. application 객체 준비
		ServletContext application = sce.getServletContext();
		// 2. application 객체에 저장된 접속자 카운트 데이터 수정
		application.setAttribute("total", 0);
		application.setAttribute("current", 0);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
//		HttpSession session = se.getSession();
//		session.setMaxInactiveInterval(30);
		
		// 1. application 객체 준비
		ServletContext application = se.getSession().getServletContext();
		// 2-1. application 객체에 저장된 접속자 카운트 데이터 읽기
		int total = (int)application.getAttribute("total");
		int current = (int)application.getAttribute("current");
		// 2-2. application 객체에 저장된 접속자 카운트 데이터 수정
		application.setAttribute("total", total + 1);	// 누적 접속자수 증가
		application.setAttribute("current", current + 1); // 현재 접속자수 증가
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 1. application 객체 준비
		ServletContext application = se.getSession().getServletContext();
		// 2-1. application 객체에 저장된 접속자 카운트 데이터 읽기
		int current = (int)application.getAttribute("current");
		// 2-2. application 객체에 저장된 접속자 카운트 데이터 수정
		application.setAttribute("current", current - 1); // 현재 접속자수 증가
	}

}
