package com.demoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// @RequestMapping(path = { "/", "/home" }, method = RequestMethod.GET)
	@GetMapping(path = { "/", "/home" })
	public String home() {
		
		return "home"; // "/WEB-INF/views/" + home + ".jsp"
	}
	
}
