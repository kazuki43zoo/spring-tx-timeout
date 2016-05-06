package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.model.Todo;
import com.example.domain.service.TodoService2;
import com.example.domain.service.TodoService3;
import com.example.domain.service.TodoService4;

@RestController
@RequestMapping("/todo4")
public class TodoController4 {

	@Autowired
	TodoService4 todoService4;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> sleepBeforePostToService(@RequestBody Todo todo) {
		System.out.println("sleepDuringPostToService : " + todo);
		todoService4.insert(todo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<List<Todo>> get() {
		return todoService4.selectAll();
	}
}