package com.example.cykashoppinglist.service;

import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.entity.TodoEntry;

import java.util.List;

public interface TodoService {
	List<TodoEntry> getAll();
}
