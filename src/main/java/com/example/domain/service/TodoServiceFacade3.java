package com.example.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.domain.model.Todo;

@Service
public class TodoServiceFacade3 {

	@Autowired
	TodoService1 todoService1;
	@Autowired
	TodoService3 todoService3;
	@Autowired
	TodoService4 todoService4;

	@Autowired
	@Qualifier(value = "setTransactionTemplateToFacade3")
	TransactionTemplate transactionTemplate;

	public Object insert(Todo todo) {
		return transactionTemplate.execute(status -> {
			System.out.println(TodoServiceFacade3.class.getName());
			todoService1.insert(todo);
			todoService3.insert(todo);
			todoService4.insert(todo);
			return null;
		});
	}

	public List<List<Todo>> selectAll() {
		return transactionTemplate.execute(status -> {
			ArrayList<List<Todo>> list = new ArrayList<>();
			list.add(todoService1.selectAll());
			list.add(todoService3.selectAll().get(0));
			list.add(todoService4.selectAll().get(0));
			return list;
		});
	}
}