package com.example.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;
import com.example.domain.repository.TodoRepository5;

@Service
@Transactional()
public class TodoService5 {
	
	@Autowired
	TodoRepository5 todoRepository;
	
	
	public void insert(Todo todo){
		todoRepository.insert(todo);
	}

	public List<Todo> selectAll(){
		return todoRepository.selectAll();
	}
}