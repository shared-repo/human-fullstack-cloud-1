package com.myweb.bootmywebref.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		ToDoEntity entity = ToDoEntity.fromDto(toDo);
		toDoRepository.save(entity);
		
	}

	@Override
	public List<ToDoDto> findAllToDos() {
		List<ToDoEntity> toDos = toDoRepository.findAll();
		ArrayList<ToDoDto> toDos2 = new ArrayList<>();
		for (ToDoEntity toDo : toDos) {
			ToDoDto toDo2 = toDo.toDto();
			toDos2.add(toDo2);
		}
		
		return toDos2;
	}

	@Override
	public ToDoDto findToDoByIdx(int idx) {
		
		Optional<ToDoEntity> toDo = toDoRepository.findById(idx);
		if (toDo.isPresent()) {
			ToDoEntity toDo2 = toDo.get();
			return toDo2.toDto();	
		}
		
		return null;
	}

	@Override
	public void deleteToDo(int idx) {
		
		toDoRepository.deleteById(idx);
		
	}

	@Override
	public void updateToDo(ToDoDto toDo) {
		
		// insert
		// ToDoEntity entity = new ToDoEntity();
		// toDoRepository.save(entity);
		
		// update 1
		// ToDoEntity entity = new ToDoEntity();
		// entity.setIdx(toDo.getIdx());
		// toDoRepository.save(entity);
		
		// update 2
		Optional<ToDoEntity> toDo2 = toDoRepository.findById(toDo.getIdx());
		if (toDo2.isPresent()) {
			ToDoEntity entity = toDo2.get();
			entity.setTitle(toDo.getTitle());
			entity.setContent(toDo.getContent());
			entity.setCompleted(toDo.isCompleted());
			toDoRepository.save(entity);
		}
	}


}






