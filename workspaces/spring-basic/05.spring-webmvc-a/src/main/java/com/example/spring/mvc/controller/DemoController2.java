package com.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.mvc.dto.PersonDto;

@Controller
@RequestMapping(path = { "/demo2" })
public class DemoController2 {
		
	// 3. View로 데이터 전달
	@GetMapping(path = { "/param" }) // GET 요청만 처리하는 메서드
	public String processParam(
			@RequestParam(name = "data1") String data1, 
			@RequestParam("data2") int data2,
			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 통로
		
		System.out.println("----------------> " + data1 + " / " + data2);
		
		model.addAttribute("data1", data1); // Model 타입 전달인자에 데이터를 저장하면 View로 전달
		model.addAttribute("data2", data2); // 실제로는 request 객체에 저장됩니다.
		
		return "demo/result"; // "/WEB-INF/views/" + "demo/result" + ".jsp"
	}
	
	// 4. DTO 전달인자 객체를 사용해서 요청 데이터 읽기
	@PostMapping(path = { "/param" }) // POST 요청만 처리하는 메서드
	public String processParam2(
			// PersonDto person,
			@ModelAttribute("person") PersonDto person, // @ModelAttribute : View로 데이터 전달
			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 통로
		
		System.out.println("----------------> " + person);
		
		model.addAttribute("person2", person);
		
		
		return "demo/result"; // "/WEB-INF/views/" + "demo/result" + ".jsp"
	}
	
	// 5. redirect
	@GetMapping(path = { "/redirect" })
	public String redirect() {
	
		System.out.println("-------------------> home으로 redirect");
		return "redirect:/home"; 
	}
	
	// 6. forward
	@GetMapping(path = { "/forward" })
	public String forward() {
	
		System.out.println("-------------------> 으로 forward");
		return "forward:/resources/forward-result.html"; 
	}
	
}












