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
import com.example.domain.service.TodoService1;
import com.example.domain.service.TodoServiceFacade1;

@RestController
@RequestMapping("/todo1")
public class TodoController1 {

	@Autowired
	TodoService1 todoService1;

	@Autowired
	TodoServiceFacade1 todoServiceFacade1;

	/**
	 * 普通のPOST
	 * @param todo
	 * @return ステータスコード
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> postToService(@RequestBody Todo todo) {
		System.out.println("postToService : " + todo);
		todoService1.insert(todo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	/**
	 * FacadeサービスへのPOST
	 * @param todo
	 * @return ステータスコード
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/facade", method = RequestMethod.POST)
	public ResponseEntity<String> postToServiceFacade(@RequestBody Todo todo) {
		System.out.println("postToServiceFacade : " + todo);
		todoServiceFacade1.insert(todo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	/**
	 * TodoリストのPOST
	 * @param todos
	 * @return ステータスコード
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<String> postListToService(@RequestBody List<Todo> todos) {
		System.out.println("postListToService : " + todos.get(0));
		todoService1.insertList(todos);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Todo> get() {
		return todoService1.selectAll();
	}
}