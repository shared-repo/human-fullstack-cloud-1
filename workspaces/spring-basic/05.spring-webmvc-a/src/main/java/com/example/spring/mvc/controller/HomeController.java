package com.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Component와 같은 역할 + 웹 기능 추가
public class HomeController {

	@RequestMapping(path = { "/", "home" }) // @WebServlet과 비슷한 역할
	public String home() {
		
		return "home";	// "/WEB-INF/views/" + "home" + ".jsp"
	}
	
}
