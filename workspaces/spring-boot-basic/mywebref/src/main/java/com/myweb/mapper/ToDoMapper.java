package com.myweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.dto.ToDoDto;

@Mapper
public interface ToDoMapper {

	void insertToDo(ToDoDto toDo);
	List<ToDoDto> selectAllToDo();
	ToDoDto selectToDoByIdx(int idx);
	void deleteToDo(int idx);
	void updateToDo(ToDoDto toDo);
	
}
