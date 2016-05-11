package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.model.Todo;
import com.example.domain.service.TodoService2;

@RestController
@RequestMapping("/todo2")
public class TodoController2 {

	@Autowired
	TodoService2 todoService2;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> sleepAfterPostToService(@RequestBody Todo todo) {
		System.out.println("sleepAfterPostToService : " + todo);
		todoService2.insert(todo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}


	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<List<Todo>> get() {
		return todoService2.selectAll();
	}
}