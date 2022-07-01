package com.example.cykashoppinglist.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.cykashoppinglist.MainActivity;
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

	public ServiceManager() {
		instance = this;
		this.items = new ArrayList<>();
		this.shoppingListService = new ShoplistServiceImpl();
		this.todoService = new TodoServiceImpl();

		shoppingListName = MainActivity.mainContext.getResources().getString(R.string.shoppingListServiceName);
		todoListName = MainActivity.mainContext.getResources().getString(R.string.todoListServiceName);

		activeService = shoppingListService;
	}

	public void getAll() {
		activeService.getAll();
	}

	public List<Item> getListReference() {
		return this.items;
	}

	@SuppressLint("NotifyDataSetChanged")
	public void getCallback(List<Item> response) {
		// There's probably a better way *not* removing all the entries but I can't be bothered
		this.items.removeIf(entry -> true);
		this.items.addAll(response);
		this.adapter.notifyDataSetChanged();
	}

	public void postItem(GenericItem item) {
		activeService.postItem(item);
	}

	public void postCallback(Item item) {
		this.items.add(item);
		this.adapter.notifyItemInserted(this.items.size() - 1);
	}

	public void delete(Integer position) {
		activeService.deleteItem(this.items.get(position));
	}

	public void deleteCallback(Long id) {
		int pos = -1;
		Item foundItem = null;
		for (Item item : this.items) {
			if (item.getId() == id) {
				pos = this.items.indexOf(item);
				foundItem = item;
				break;
			}
		}
		if (pos == -1) {
			return;
		}
		this.items.remove(foundItem);
		this.adapter.notifyItemRemoved(pos);
	}

	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
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
