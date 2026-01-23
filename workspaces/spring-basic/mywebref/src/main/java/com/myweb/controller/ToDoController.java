package com.myweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myweb.dto.ToDoDto;
import com.myweb.mapper.ToDoMapper;

@Controller
public class ToDoController {
	
	private ToDoMapper toDoMapper;	
	public ToDoController(ToDoMapper toDoMapper) {
		this.toDoMapper = toDoMapper;
	}

	@GetMapping(path = { "/todo/list" })
	public String list(Model model) {
		List<ToDoDto> toDos = toDoMapper.selectAllToDo();
		model.addAttribute("todos", toDos);
		
		return "todo/list";
	}
	
	@GetMapping(path = { "/todo/write" })
	public String writeForm() {
		
		return "todo/write";
		
	}
	
	@PostMapping(path = { "/todo/write" })
	public String write(ToDoDto todo) {
		
		System.out.println("-----------------------> " + todo);
		toDoMapper.insertToDo(todo);
		
		return "redirect:/todo/list";
		
	}

}
