package com.myweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.dto.ToDoDto;

@Mapper
public interface ToDoMapper {

	void insertToDo(ToDoDto todo);
	List<ToDoDto> selectAllToDo();
	
}
