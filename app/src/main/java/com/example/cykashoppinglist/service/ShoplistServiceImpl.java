package com.example.cykashoppinglist.service;

import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.mapper.ShoplistMapper;

public class ShoplistServiceImpl implements RestService {
	private final String url;

	public ShoplistServiceImpl() {
//		todo maybe move these to like strings or some other constant file
		this.url = MainActivity.mainContext.getResources().getString(R.string.host) + MainActivity.mainContext.getResources().getString(R.string.shoppingList);
	}

	@Override
	public void getAll() {
		doGet(url, ShoplistMapper.class);
	}

	@Override
	public void postItem(GenericItem item) {
		doPost(item, url, ShoplistMapper.class);
	}

	@Override
	public void deleteItem(Item item) {
		doDelete(item, url);
	}
}
