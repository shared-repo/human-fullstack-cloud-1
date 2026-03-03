package com.humanda6.demoweb2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // flask의 Blueprint 역할, 자동으로 등록
public class HomeController {
	
	@GetMapping(path = { "/", "/index" }) // flask의 @...route 역할
	public String index() {
		
		return "index"; // -> /templates/index.html, ( flask의 render_template('index.html') ) 
	}

}
