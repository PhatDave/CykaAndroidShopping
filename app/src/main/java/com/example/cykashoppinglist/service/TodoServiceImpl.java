package com.example.cykashoppinglist.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.TodoEntry;
import com.example.cykashoppinglist.mapper.ShoplistMapper;
import com.example.cykashoppinglist.mapper.TodoMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceImpl implements RestService {
	private final RequestQueue requestQueue;
	private final String url;
	private Adapter adapter;
	private final List<Item> todoEntries;

	public TodoServiceImpl(Context context, List<Item> items) {
		this.requestQueue = Volley.newRequestQueue(context);
//		todo maybe move these to like strings or some other constant file
		this.url = context.getResources().getString(R.string.host) + context.getResources().getString(R.string.todoList);
		this.todoEntries = items;
	}

	@Override
	public List<Item> getAll() {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
			response -> {
				this.handleResponse(TodoMapper.map(response));
			},
			error -> {
				System.out.println("Uhoh!" + error);
			}
		);

		this.requestQueue.add(request);
		return this.todoEntries;
	}

	@Override
	public List<Item> getListReference() {
		return this.todoEntries;
	}

	@SuppressLint("NotifyDataSetChanged")
	@Override
	public void handleResponse(List<Item> response) {
		// This must be done to preserve the reference to this.shoplistEntries
		// There's probably a better way *not* removing all the entries but I can't be bothered
		this.todoEntries.removeIf(entry -> true);
		this.todoEntries.addAll(response);
		this.adapter.notifyDataSetChanged();
	}

	@Override
	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}
}
