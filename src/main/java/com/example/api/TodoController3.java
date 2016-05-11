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
import com.example.domain.service.TodoService3;

@RestController
@RequestMapping("/todo3")
public class TodoController3 {

	@Autowired
	TodoService3 todoService3;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> sleepDuringPostToService(@RequestBody Todo todo) {
		System.out.println("sleepDuringPostToService : " + todo);
		todoService3.insert(todo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<List<Todo>> get() {
		return todoService3.selectAll();
	}
}