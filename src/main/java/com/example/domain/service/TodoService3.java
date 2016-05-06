package com.example.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Todo;
import com.example.domain.repository.TodoRepository1;
import com.example.domain.repository.TodoRepository2;

@Service
@Transactional//(timeout = 10)
public class TodoService3 {
	@Autowired
	TodoRepository1 todoRepository1;
	@Autowired
	TodoRepository2 todoRepository2;

	/**
	 * 追加後にわざとsleepをかける
	 * 
	 * @param todo
	 */
	public void insert(Todo todo) {
		long start = System.currentTimeMillis();
		todoRepository1.insert(todo);
		sleep();
		todoRepository2.insert(todo);

		System.out.println("処理時間：" + (System.currentTimeMillis() - start));
	}

	public List<Todo> select(int id) {
		ArrayList<Todo> list = new ArrayList<>();
		list.add(todoRepository1.select(id));
		list.add(todoRepository2.select(id));
		return list;
	}

	public List<List<Todo>> selectAll() {
		ArrayList<List<Todo>> list = new ArrayList<>();
		list.add(todoRepository1.selectAll());
		list.add(todoRepository2.selectAll());
		return list;
	}

	private void sleep() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}