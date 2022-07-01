package com.example.cykashoppinglist.service;

import android.content.Context;

import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
	private final RestService shoppingListService;
	private final RestService todoService;
	private final List<Item> items;

	private final String shoppingListName;
	private final String todoListName;

	private RestService activeService;

	public ServiceManager(Context context) {
		this.items = new ArrayList<>();
		this.shoppingListService = new ShoplistServiceImpl(context, items);
		this.todoService = new TodoServiceImpl(context, items);

		shoppingListName = context.getResources().getString(R.string.shoppingListServiceName);
		todoListName = context.getResources().getString(R.string.todoListServiceName);

		activeService = shoppingListService;
	}

	public List<Item> getAll() {
		return activeService.getAll();
	}

	public List<Item> getListReference() {
		return this.items;
	}

	public Item postItem(Item item) {
		return activeService.postItem(item);
	}

	public void setAdapter(Adapter adapter) {
		shoppingListService.setAdapter(adapter);
		todoService.setAdapter(adapter);
	}

	// this is bad but I don't think the application ever should expand to more than 2 services
	public void switchService(String serviceName) {
		if (serviceName.equals(shoppingListName)) {
			activeService = shoppingListService;
		} else if (serviceName.equals(todoListName)) {
			activeService = todoService;
		}
		this.getAll();
	}
}
