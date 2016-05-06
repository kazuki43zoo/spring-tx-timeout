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
import com.example.domain.service.TodoServiceFacade2;

@RestController
@RequestMapping("/facade2")
public class TodoControllerFacade2 {

	@Autowired
	TodoServiceFacade2 todoServiceFacade2;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody Todo todo) {
		System.out.println("新規登録 : " + todo);
		todoServiceFacade2.insert(todo);
		return new ResponseEntity<String>(HttpStatus.CREATED);

	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Todo> get() {
		return todoServiceFacade2.selectAll().get(0);
	}
}