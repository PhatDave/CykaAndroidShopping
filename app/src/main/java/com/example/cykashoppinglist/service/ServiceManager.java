package com.example.cykashoppinglist.service;

import android.content.Context;

import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
	private final RestService shoppingListService;
	private final RestService todoService;
	private final List<Item> items;
	private Adapter adapter;
	private static ServiceManager instance;

	private final String shoppingListName;
	private final String todoListName;

	private RestService activeService;

	public static ServiceManager getInstance() {
		// New one can not be created because context is missing, we assume this won't be called before mainActivity
		return instance;
	}

	public ServiceManager(Context context) {
		instance = this;
		this.items = new ArrayList<>();
		this.shoppingListService = new ShoplistServiceImpl(context, items);
		this.todoService = new TodoServiceImpl(context, items);

		shoppingListName = context.getResources().getString(R.string.shoppingListServiceName);
		todoListName = context.getResources().getString(R.string.todoListServiceName);

		activeService = shoppingListService;
	}

	public void getAll() {
		activeService.getAll();
	}

	public List<Item> getListReference() {
		return this.items;
	}

	public void postItem(GenericItem item) {
		activeService.postItem(item);
	}

	public void addItem(Item item) {
		this.items.add(item);
		this.adapter.notifyItemInserted(this.items.size() - 1);
	}

	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
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
