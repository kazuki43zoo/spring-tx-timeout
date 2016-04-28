package com.example.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.domain.model.Todo;

@Repository
public interface TodoRepository3 {
	// 追加
    void insert(Todo todo);
    // 1件取得
    Todo select(int id);
    // 全件取得
    List<Todo> selectAll();
}