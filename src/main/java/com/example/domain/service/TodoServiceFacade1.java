package com.example.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;

@Service
@Transactional
public class TodoServiceFacade1 {

	@Autowired
	TodoService1 todoService1;
	@Autowired
	TodoService2 todoService2;
	@Autowired
	TodoService3 todoService3;

	public void insert(Todo todo) {
		todoService1.insert(todo);
		todoService2.insert(todo);
		todoService3.insert(todo);
	}

	public List<Todo> select(int id) {
		ArrayList<Todo> list = new ArrayList<>();
		list.add(todoService1.select(id));
		list.add(todoService2.select(id));
		list.add(todoService3.select(id));
		return list;
	}

	public List<List<Todo>> selectAll() {
		ArrayList<List<Todo>> list = new ArrayList<>();
		list.add(todoService1.selectAll());
		list.add(todoService2.selectAll());
		list.add(todoService3.selectAll());
		return list;
	}
}