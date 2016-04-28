package com.example.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;
import com.example.domain.repository.TodoRepository3;

@Service
@Transactional
public class TodoService3 {
	
	@Autowired
	TodoRepository3 todoRepository;
	
	
	public void insert(Todo todo){
		todoRepository.insert(todo);
	}
	
	public Todo select(int id){
		return todoRepository.select(id);
	}
	
	public List<Todo> selectAll(){
		return todoRepository.selectAll();
	}
}