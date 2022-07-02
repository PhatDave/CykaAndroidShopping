package com.example.cykashoppinglist.service;

import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.mapper.TodoMapper;

public class TodoService implements RestService {
	private final String url;

	public TodoService() {
		this.url = MainActivity.mainContext.getResources().getString(R.string.host) + MainActivity.mainContext.getResources().getString(R.string.todoList);
	}

	@Override
	public void getAll() {
		doGet(url, TodoMapper.class);
	}

	@Override
	public void postItem(GenericItem item) {
		doPost(item, url, TodoMapper.class);
	}

	@Override
	public void deleteItem(Item item) {
		doDelete(item, url);
	}
}
