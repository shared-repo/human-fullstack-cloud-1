package com.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.example.spring.mvc.dto.PersonDto;
import com.example.spring.mvc.view.MyView;

@Controller
public class DemoController {
	
	// 1. HttpServeltRequest 전달인자로 데이터 읽기
//	@RequestMapping(path = { "/demo/param" })
//	public String processParam(HttpServletRequest req) {
//		
//		String data1 = req.getParameter("data1");
//		String data2 = req.getParameter("data2");
//		
//		System.out.println("----------------> " + data1 + " / " + data2);
//		
//		return "demo/result"; // "/WEB-INF/views/" + "demo/result" + ".jsp"
//	}
	
	// 2. 전달인자를 통해 요청 데이터 직접 수신
	// @RequestMapping(path = { "/demo/param" }, method = RequestMethod.GET)
//	@GetMapping(path = { "/demo/param" }) // GET 요청만 처리하는 메서드
//	public String processParam(
//			@RequestParam(name = "data1") String data1, 
//			@RequestParam("data2") int data2) {
//		
//		System.out.println("----------------> " + data1 + " / " + data2);
//		
//		return "demo/result"; // "/WEB-INF/views/" + "demo/result" + ".jsp"
//	}
	
	// 3. View로 데이터 전달
	@GetMapping(path = { "/demo/param" }) // GET 요청만 처리하는 메서드
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
	@PostMapping(path = { "/demo/param" }) // POST 요청만 처리하는 메서드
	public String processParam2(
			// PersonDto person,
			@ModelAttribute("person") PersonDto person, // @ModelAttribute : View로 데이터 전달
			Model model) { // Model 타입 전달인자는 View로 데이터를 전달하는 통로
		
		System.out.println("----------------> " + person);
		
		model.addAttribute("person2", person);
		
		
		return "demo/result"; // "/WEB-INF/views/" + "demo/result" + ".jsp"
	}
	
	// 5. redirect
	@GetMapping(path = { "/demo/redirect" })
	public String redirect() {
	
		System.out.println("-------------------> home으로 redirect");
		return "redirect:/home"; 
	}
	
	// 6. forward
	@GetMapping(path = { "/demo/forward" })
	public String forward() {
	
		System.out.println("-------------------> 으로 forward");
		return "forward:/resources/forward-result.html"; 
	}
	
	// 8. Custom View
	@GetMapping(path = { "/demo/custom-view" })
	public View customView(Model model) {
		
		model.addAttribute("mbti", "INTP");
		model.addAttribute("bloodtype", "A");
		
		MyView view = new MyView();
	
		return view; // View를 반환하면 기존 ViewResolver를 사용하지 않고 반환한 View 사용
	}
	
	// 9. HTML이 아닌 데이터 반환 ( plain text, json, xml, ... )
	@GetMapping(path = { "/demo/ajax" }, produces = { "text/plain;charset=utf-8" })
	@ResponseBody // 반환값을 jsp 이름으로 사용하지 말고 그대로 응답으로 처리
	public String ajax() {
		
		double n = Math.floor(Math.random() * 900) + 100;
	
		return "오늘의 행운 번호 " + n; // 파일이름으로 해성
	}
	
}












