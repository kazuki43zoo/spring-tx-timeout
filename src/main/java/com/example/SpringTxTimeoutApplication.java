package com.example;

import com.example.domain.model.Todo;
import com.example.domain.service.TodoService1;
import com.example.domain.service.TodoServiceFacade1;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = { "com.example.domain.repository" })
@SpringBootApplication
public class SpringTxTimeoutApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringTxTimeoutApplication.class, args);
	}

	@Autowired
	TodoServiceFacade1 todoServiceFacade1;

	@Override
	public void run(String... args) throws Exception {
		Todo newTodo = new Todo();
		newTodo.setTitle("飲み会");
		newTodo.setDetails("銀座 19:00");
		// 新しいTodoをインサートする
		todoServiceFacade1.insert(newTodo);
		// インサートしたTodoを取得して標準出力する
		List<Todo> loadedTodo = todoServiceFacade1.select(newTodo.getId());
		for (Todo todo : loadedTodo) {
			System.out.println(todo);
		}
		newTodo = new Todo();
		newTodo.setTitle("食事会");
		newTodo.setDetails("新宿 20:00");
		// 新しいTodoをインサートする
		todoServiceFacade1.insert(newTodo);
		System.out.println("\nこれからの予定\n");
		// 全件取得
		List<List<Todo>> loadedTodos = todoServiceFacade1.selectAll();
		for (List<Todo> todos : loadedTodos) {
			for (Todo todo : todos) {
				System.out.println(todo);
			}
		}
		// System.exit(0);
	}
}