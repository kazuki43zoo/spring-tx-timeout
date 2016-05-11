package com.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.example.domain.model.Todo;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTxTimeoutApplication.class)
@WebAppConfiguration
public class SpringTxTimeoutApplicationTests {

	final static String URL1 = "http://localhost:7777/todo1";
	final static String URL2 = "http://localhost:7777/todo2";
	final static String URL3 = "http://localhost:7777/todo3";
	final static String URL4 = "http://localhost:7777/todo4";
	final static String URL5 = "http://localhost:7777/facade1";
	final static String URL6 = "http://localhost:7777/todo1/list";
	final static String URL7 = "http://localhost:7777/facade2";
	final static String URL8 = "http://localhost:7777/facade3";

	/**
	 * 普通の登録テスト
	 */
	@Test
	public void normal() {
		RestTemplate restTemplate = new RestTemplate();
		// オブジェクトを作成し
		Todo todo = new Todo("Todo1", "詳細1");
		HttpEntity<Todo> request = new HttpEntity<>(todo);
		// DBにPOST
		restTemplate.postForObject(URL1, request, ResponseEntity.class);
		// GETで内容を取得
		ResponseEntity<List> entity = restTemplate.getForEntity(URL1, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
		// 登録した内容の確認
		List<Todo> list = entity.getBody();
		System.out.println(todo + "," + list.get(0));
	}

	/**
	 * 2件登録してからスリープをかけるテスト
	 */
	@Test
	public void serviceTimeoutAfterInsertToRepositories() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo2", "詳細2");

		HttpEntity<Todo> request = new HttpEntity<>(todo);

		ResponseEntity entity = restTemplate.postForEntity(URL2, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());

		entity = restTemplate.getForEntity(URL2, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
		// 登録した内容の確認
		List<List<Todo>> list = (List<List<Todo>>) entity.getBody();
		for (List todoList : list) {
			System.out.println(todoList.get(0));
		}
	}

	/**
	 * 1件登録してスリープして、また1件登録するテスト
	 */
	@Test
	public void serviceTimeoutDuringInsertToRepositories() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo3", "詳細3");

		HttpEntity<Todo> request = new HttpEntity<>(todo);

		ResponseEntity entity = restTemplate.postForEntity(URL3, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());

		entity = restTemplate.getForEntity(URL3, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
		// 登録した内容の確認
		List<List<Todo>> list = (List<List<Todo>>) entity.getBody();
		for (List todoList : list) {
			System.out.println(todoList.get(0));
		}
	}

	/**
	 * スリープしてから2件登録するテスト
	 */
	@Test
	public void serviceTimeoutBeforeInsertToRepositories() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo4", "詳細4");

		HttpEntity<Todo> request = new HttpEntity<>(todo);

		ResponseEntity entity = restTemplate.postForEntity(URL4, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());

		entity = restTemplate.getForEntity(URL4, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
		// 登録した内容の確認
		List<List<Todo>> list = (List<List<Todo>>) entity.getBody();
		for (List todoList : list) {
			System.out.println(todoList.get(0));
		}
	}

	/**
	 * ServiceFacadeのテスト
	 */
	@Test
	public void serviceFacadeTimeout() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo5", "詳細5");

		HttpEntity<Todo> request = new HttpEntity<>(todo);

		ResponseEntity entity = restTemplate.postForEntity(URL5, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());

		entity = restTemplate.getForEntity(URL5, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
		// 登録した内容の確認
		List<List<Todo>> list = (List<List<Todo>>) entity.getBody();
		for (List<Todo> todoList : list) {
			System.out.println(todoList.get(0));
		}
	}

	/**
	 * クエリ実行中にタイムアウトした場合のテスト
	 */
	@Test
	public void serviceFacadeQueryTimeout() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo6", "詳細6");
		List<Todo> todos = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			todos.add(todo);
		}

		HttpEntity<List<Todo>> request = new HttpEntity<>(todos);
		System.out.println("リスト作成");
		ResponseEntity entity = restTemplate.postForEntity(URL6, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());
		entity = restTemplate.getForEntity(URL6, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
	}
	/**
	 * TransactionTemplateを使ったタイムアウトのテスト1
	 */
	@Test
	public void serviceFacadeTemplateTimeout1() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo7", "詳細7");
		HttpEntity<Todo> request = new HttpEntity<>(todo);
		System.out.println("リスト作成");
		ResponseEntity entity = restTemplate.postForEntity(URL7, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());
		entity = restTemplate.getForEntity(URL7, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
	}

	/**
	 * TransactionTemplateを使ったタイムアウトのテスト2
	 */
	@Test
	public void serviceFacadeTemplateTimeout2() {
		RestTemplate restTemplate = new RestTemplate();
		Todo todo = new Todo("Todo8", "詳細8");
		HttpEntity<Todo> request = new HttpEntity<>(todo);
		System.out.println("リスト作成");
		ResponseEntity entity = restTemplate.postForEntity(URL8, request, ResponseEntity.class);
		System.out.println(entity.getStatusCode());
		entity = restTemplate.getForEntity(URL8, List.class);
		// StatusCodeの確認
		System.out.println(entity.getStatusCode() + "" + HttpStatus.OK);
	}

}
