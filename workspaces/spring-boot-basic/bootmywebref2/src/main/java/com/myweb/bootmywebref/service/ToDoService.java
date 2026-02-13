package com.myweb.bootmywebref.service;

import java.util.List;

import com.myweb.bootmywebref.dto.ToDoDto;

public interface ToDoService {

	void writeToDo(ToDoDto todo);

	List<ToDoDto> findAllToDos();

	ToDoDto findToDoByIdx(int idx);

	void deleteToDo(int idx);

	void updateToDo(ToDoDto toDo);

}
