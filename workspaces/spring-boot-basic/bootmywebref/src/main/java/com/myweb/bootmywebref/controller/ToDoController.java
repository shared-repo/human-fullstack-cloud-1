package com.myweb.bootmywebref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.bootmywebref.dto.ToDoDto;
import com.myweb.bootmywebref.service.ToDoService;

@Controller
@RequestMapping(path = { "/todo" })
public class ToDoController {
	
	private ToDoService toDoService;
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

//	@GetMapping(path = { "/todo/list" })
	@GetMapping(path = { "/list" })
	public String list() {
		
		return "todo/list";	// "/templates/" + todo/list + ".html"
	}
	
	@GetMapping(path = { "/write" })
	public String writeForm() {
		
		return "todo/write";
	}
	
	@PostMapping(path = { "/write" })
	public String write(ToDoDto todo) {
		
		// System.out.println("-----------------------> " + todo);
		toDoService.writeToDo(todo);
		
		return "redirect:/todo/list";
		
	}
	
}



