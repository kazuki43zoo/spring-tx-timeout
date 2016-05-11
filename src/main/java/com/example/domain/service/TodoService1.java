package com.example.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;
import com.example.domain.repository.TodoRepository1;

@Service
@Transactional
public class TodoService1 {

	@Autowired
	TodoRepository1 todoRepository;

	public void insert(Todo todo) {
		todoRepository.insert(todo);
	}

	public List<Todo> selectAll() {
		return todoRepository.selectAll();
	}

	@Transactional(timeout = 1)
	public void insertList(List<Todo> todos) {
		System.out.println("書き込み開始");
		todoRepository.insertList(todos);
		System.out.println("書き込み終了");
	}
}