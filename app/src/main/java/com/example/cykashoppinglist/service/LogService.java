package com.example.cykashoppinglist.service;

import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.mapper.LogMapper;

public class LogService implements RestService {
	private final String url;

	public LogService() {
		this.url = MainActivity.mainContext.getResources().getString(R.string.host) + MainActivity.mainContext.getResources().getString(R.string.log) + "/" + MainActivity.mainContext.getResources().getString(R.string.logCount);
	}

	@Override
	public void getAll() {
		doGet(url, LogMapper.class);
	}

	@Override
	public void postItem(GenericItem item) {
		return;
	}

	@Override
	public void deleteItem(Item item) {
		return;
	}
}
