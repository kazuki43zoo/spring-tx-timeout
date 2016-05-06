package com.example.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;

@Service
@Transactional
public class TodoServiceFacade2 {

	@Autowired
	TodoService1 todoService1;
	@Autowired
	TodoService5 todoService5;

	public void insert(Todo todo) {
		todoService1.insert(todo);
		todoService1.update(todo);
		todoService5.insert(todo);
	}

	public List<List<Todo>> selectAll() {
		ArrayList<List<Todo>> list = new ArrayList<>();
		list.add(todoService1.selectAll());
		list.add(todoService5.selectAll());
		return list;
	}
}