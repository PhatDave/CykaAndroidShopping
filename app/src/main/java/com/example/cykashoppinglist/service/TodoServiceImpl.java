package com.example.cykashoppinglist.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.TodoEntry;
import com.example.cykashoppinglist.mapper.TodoMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceImpl implements RestService {
	private final RequestQueue requestQueue;
	private final String url;
	private List<Item> todoEntries;

	public TodoServiceImpl(Context context) {
		this.requestQueue = Volley.newRequestQueue(context);
//		todo maybe move these to like strings or some other constant file
		this.url = "http://178.128.141.50:8080/todo";
		this.todoEntries = new ArrayList<>();
	}

	@Override
	public List<Item> getAll() {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
			response -> {
				this.todoEntries = TodoMapper.map(response);
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
}
