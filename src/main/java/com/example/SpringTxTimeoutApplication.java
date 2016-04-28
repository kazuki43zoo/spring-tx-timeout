package com.example;

import com.example.domain.model.Todo;
import com.example.domain.service.TodoService;

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
	TodoService todoService;

	@Override
	public void run(String... args) throws Exception {
		Todo newTodo = new Todo();
		newTodo.setTitle("飲み会");
		newTodo.setDetails("銀座 19:00");
		// 新しいTodoをインサートする
		todoService.insert(newTodo);
		// インサートしたTodoを取得して標準出力する
		Todo loadedTodo = todoService.select(newTodo.getId());
		System.out.println("ID       : " + loadedTodo.getId());
		System.out.println("TITLE    : " + loadedTodo.getTitle());
		System.out.println("DETAILS  : " + loadedTodo.getDetails());
		System.out.println("FINISHED : " + loadedTodo.isFinished());
		newTodo = new Todo();
		newTodo.setTitle("食事会");
		newTodo.setDetails("新宿 20:00");
		// 新しいTodoをインサートする
		todoService.insert(newTodo);
		System.out.println("\nこれからの予定\n");
		// 全件取得
		List<Todo> loadedTodos = todoService.selectAll();
		for (Todo todo : loadedTodos) {
			System.out.println("ID       : " + todo.getId());
			System.out.println("TITLE    : " + todo.getTitle());
			System.out.println("DETAILS  : " + todo.getDetails());
			System.out.println("FINISHED : " + todo.isFinished());
		}
		System.exit(0);
	}
}