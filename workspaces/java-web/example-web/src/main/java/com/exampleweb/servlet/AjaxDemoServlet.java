package com.exampleweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/request-data" })
public class AjaxDemoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			Thread.sleep(5000); // 5초간 중지(멈춤)
		} catch (InterruptedException e) {}
		
		// 날짜 -> 지정된 형식의 문자열 (format), 지정된 형식의 문자열 -> 날짜 (parse) 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
		String data = sdf.format(new Date());
		
		resp.setContentType("text/plain;charset=utf-8"); // 한글 지원 설정
		PrintWriter out = resp.getWriter();
		out.println(data);
		
	}

}




