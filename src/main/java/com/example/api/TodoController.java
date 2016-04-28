package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.model.Todo;
import com.example.domain.service.TodoService1;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	TodoService1 todoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void post(@ModelAttribute Todo todo) {
		todoService.insert(todo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Todo> get() {
		return todoService.selectAll();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Todo get(@PathVariable int id) {
		return todoService.select(id);
	}

}