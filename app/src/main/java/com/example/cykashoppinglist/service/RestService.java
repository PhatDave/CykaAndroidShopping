package com.example.cykashoppinglist.service;

import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.TodoEntry;

import java.util.List;

public interface RestService {
	List<Item> getAll();
	List<Item> getListReference();
}
