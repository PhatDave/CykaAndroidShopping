package com.example.cykashoppinglist.service;

import android.annotation.SuppressLint;

import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceManager {
	private final List<Item> items;
	private Adapter adapter;
	private static ServiceManager instance;

	private final Map<String, RestService> services;

	private RestService activeService;

	public static ServiceManager getInstance() {
		// New one can not be created because context is missing, we assume this won't be called before mainActivity
		return instance;
	}

	public ServiceManager() {
		instance = this;
		this.items = new ArrayList<>();
		RestService shoppingListService = new ShoplistService();
		RestService todoService = new TodoService();
		RestService logService = new LogService();

		String shoppingListName = MainActivity.mainContext.getResources().getString(R.string.shoppingListServiceName);
		String todoListName = MainActivity.mainContext.getResources().getString(R.string.todoListServiceName);
		String loggerName = MainActivity.mainContext.getResources().getString(R.string.logServiceName);

		services = Map.of(
				shoppingListName, shoppingListService,
				todoListName, todoService,
				loggerName, logService
		);

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
		try {
			activeService.deleteItem(this.items.get(position));
		} catch (IndexOutOfBoundsException ignored) {}
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
		this.activeService = this.services.get(serviceName);
		this.getAll();
	}
}
