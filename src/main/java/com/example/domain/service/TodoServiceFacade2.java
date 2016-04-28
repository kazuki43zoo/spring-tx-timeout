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
	TodoService4 todoService4;
	@Autowired
	TodoService5 todoService5;

	public void insert(Todo todo) {
		todoService1.insert(todo);
		todoService4.insert(todo);
		todoService5.insert(todo);
	}

	public List<Todo> select(int id) {
		ArrayList<Todo> list = new ArrayList<>();
		list.add(todoService1.select(id));
		list.add(todoService4.select(id));
		list.add(todoService5.select(id));
		return list;
	}

	public List<List<Todo>> selectAll() {
		ArrayList<List<Todo>> list = new ArrayList<>();
		list.add(todoService1.selectAll());
		list.add(todoService4.selectAll());
		list.add(todoService5.selectAll());
		return list;
	}
}