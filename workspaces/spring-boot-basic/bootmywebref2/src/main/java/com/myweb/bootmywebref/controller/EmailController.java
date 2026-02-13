package com.myweb.bootmywebref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {

	@GetMapping(path = { "/write" })
	public String emailForm() {
		
		return "email/write";
	}
	
	@PostMapping(path = { "/write" })
	public String sendMail() {
		
	}
	
}
