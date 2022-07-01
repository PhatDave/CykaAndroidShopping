package com.example.cykashoppinglist.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.entity.TodoEntry;
import com.example.cykashoppinglist.mapper.ShoplistMapper;
import com.example.cykashoppinglist.mapper.TodoMapper;

import org.json.JSONException;

import java.util.List;

public class TodoServiceImpl implements RestService {
	private final String url;

	public TodoServiceImpl(Context context, List<Item> items) {
//		todo maybe move these to like strings or some other constant file
		this.url = context.getResources().getString(R.string.host) + context.getResources().getString(R.string.todoList);
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
