package com.example.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;

@Service
@PropertySource("classpath:/timeout.properties")
@Transactional(timeout = 20)
public class TodoServiceFacade1 {
//	@Value("${serviceFacade1.timeout}")
//	int time;

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

	public List<List<Todo>> selectAll() {
		ArrayList<List<Todo>> list = new ArrayList<>();
		list.add(todoService1.selectAll());
		list.add(todoService2.selectAll().get(0));
		list.add(todoService3.selectAll().get(0));
		return list;
	}
}