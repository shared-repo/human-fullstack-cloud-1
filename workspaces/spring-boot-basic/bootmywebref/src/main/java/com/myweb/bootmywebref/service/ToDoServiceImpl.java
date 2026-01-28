package com.myweb.bootmywebref.service;

import org.springframework.stereotype.Service;

import com.myweb.bootmywebref.dto.ToDoDto;
import com.myweb.bootmywebref.entity.ToDoEntity;
import com.myweb.bootmywebref.repository.ToDoRepository;

@Service // Service 영역의 빈을 강조하기 위해 사용하는 @Component의 별칭
public class ToDoServiceImpl implements ToDoService {
	
	private ToDoRepository toDoRepository;
	public ToDoServiceImpl(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}

	@Override
	public void writeToDo(ToDoDto toDo) {
		
		ToDoEntity entity = new ToDoEntity();
		entity.setTitle(toDo.getTitle());
		entity.setContent(toDo.getContent());
		entity.setCompleted(toDo.isCompleted());
		
		toDoRepository.save(entity);
		
	}

}
