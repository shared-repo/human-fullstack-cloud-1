package com.myweb.bootmywebref.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.bootmywebref.dto.ToDoDto;
import com.myweb.bootmywebref.service.ToDoService;

@Controller
@RequestMapping(path = { "/todo" })
public class ToDoController {
	
	private ToDoService toDoService;
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

//	@GetMapping(path = { "/todo/list" }) 	// 클래스의 RequestMapping이 없을 때
	@GetMapping(path = { "/list" })			// 클래스의 RequestMapping이 있을 때
	public String list(Model model) {
		
		List<ToDoDto> toDos = toDoService.findAllToDos();
		// System.out.println("-------------------> " + toDos);
		
		model.addAttribute("toDos", toDos);
		
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
	
	@GetMapping(path = { "/detail" })
	public String detailWithParam(
			@RequestParam("idx") int idx,
			Model model) {
		
		ToDoDto toDo = toDoService.findToDoByIdx(idx);
		if (toDo == null) {
			return "redirect:/todo/list";
		}
		model.addAttribute("toDo", toDo);
		
		return "todo/detail";
	}
	
	@GetMapping(path = { "/detail/{idx}" })
	public String detailWithPathVariable(
			@PathVariable("idx") int idx,
			Model model) {
		
		ToDoDto toDo = toDoService.findToDoByIdx(idx);
		if (toDo == null) {
			return "redirect:/todo/list";
		}
		model.addAttribute("toDo", toDo);
		
		return "todo/detail";
	}
	
	@GetMapping(path = { "/delete" })
	public String deleteWithParam(
			@RequestParam("idx") int idx,
			Model model) {
		
		toDoService.deleteToDo(idx);
		
		return "redirect:/todo/detail";		
	}
	
	@GetMapping(path = { "/delete/{idx}" })
	public String deleteWithPathVariable(
			@PathVariable("idx") int idx,
			Model model) {
		
		toDoService.deleteToDo(idx);
		
		return "redirect:/todo/list";
	}
	
	@GetMapping(path = { "/edit/{idx}" })
	public String editFormWithPathVariable(
			@PathVariable("idx") int idx, Model model) {
		
		ToDoDto toDo = toDoService.findToDoByIdx(idx);
		if (toDo == null) {
			return "redirect:/todo/list";
		}
		model.addAttribute("toDo", toDo);
		
		return "todo/edit";
	}
	
	@GetMapping(path = { "/edit" })
	public String editFormWithParam(
			@RequestParam("idx") int idx, Model model) {
		
		ToDoDto toDo = toDoService.findToDoByIdx(idx);
		if (toDo == null) {
			return "redirect:/todo/list";
		}
		model.addAttribute("toDo", toDo);
		
		return "todo/edit";
	}
	
	@PostMapping(path = { "/edit" })
	public String updateToDo(ToDoDto toDo) {
		
		//System.out.println("-------------------> " + toDo);
		toDoService.updateToDo(toDo);
		
		return "redirect:/todo/detail/" + toDo.getIdx();
		// return "redirect:/todo/detail?idx=" + toDo.getIdx();
	}
	
}












