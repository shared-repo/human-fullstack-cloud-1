package com.myweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		// System.out.println("-----------------------> " + todo);
		toDoMapper.insertToDo(todo);
		
		return "redirect:/todo/list";
		
	}
	
	@GetMapping(path = { "/todo/detail" })
	public String detailWithParam(
			@RequestParam("idx") int idx,
			Model model) {
		
		ToDoDto toDo = toDoMapper.selectToDoByIdx(idx);
		model.addAttribute("toDo", toDo);
		
		return "todo/detail";
		
	}
	
	@GetMapping(path = { "/todo/detail/{idx}" })
	public String detailWithPathVariable(
			@PathVariable("idx") int idx,
			Model model) {
		
		ToDoDto toDo = toDoMapper.selectToDoByIdx(idx);
		model.addAttribute("toDo", toDo);
		
		return "todo/detail";
	}
	
	@GetMapping(path = { "/todo/delete" })
	public String deleteWithParam(
			@RequestParam("idx") int idx,
			Model model) {
		
		toDoMapper.deleteToDo(idx);
		
		return "todo/detail";
		
	}
	
	@GetMapping(path = { "/todo/delete/{idx}" })
	public String deleteWithPathVariable(
			@PathVariable("idx") int idx,
			Model model) {
		
		toDoMapper.deleteToDo(idx);
		
		return "redirect:/todo/list";
	}

	@GetMapping(path = { "/todo/edit/{idx}" })
	public String editFormWithPathVariable(
			@PathVariable("idx") int idx, Model model) {
		
		ToDoDto toDo = toDoMapper.selectToDoByIdx(idx);
		if (toDo == null) {
			return "redirect:/todo/list";
		}
		model.addAttribute("toDo", toDo);
		
		return "todo/edit";
	}
	
	@GetMapping(path = { "/todo/edit" })
	public String editFormWithParam(
			@RequestParam("idx") int idx, Model model) {
		
		ToDoDto toDo = toDoMapper.selectToDoByIdx(idx);
		if (toDo == null) {
			return "redirect:/todo/list";
		}
		model.addAttribute("toDo", toDo);
		
		return "todo/edit";
	}
	
	@PostMapping(path = { "/todo/edit" })
	public String updateToDo(ToDoDto toDo) {
		
		//System.out.println("-------------------> " + toDo);
		toDoMapper.updateToDo(toDo);
		
		return "redirect:/todo/detail/" + toDo.getIdx();
		// return "redirect:/todo/detail?idx=" + toDo.getIdx();
	}
	
}












