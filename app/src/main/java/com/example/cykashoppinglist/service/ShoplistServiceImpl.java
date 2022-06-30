package com.example.cykashoppinglist.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.mapper.ShoplistMapper;

import java.util.ArrayList;
import java.util.List;

public class ShoplistServiceImpl implements RestService {
	private final RequestQueue requestQueue;
	private final String url;
	private List<Item> shoplistEntries;

	public ShoplistServiceImpl(Context context) {
		this.requestQueue = Volley.newRequestQueue(context);
//		todo maybe move these to like strings or some other constant file
		this.url = "http://178.128.141.50:8080/shoppingList";
		this.shoplistEntries = new ArrayList<>();
	}

	@Override
	public List<Item> getAll() {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
			response -> {
			// todo maybe generify this with reflection
				this.shoplistEntries = ShoplistMapper.map(response);
			},
			error -> {
				System.out.println("Uhoh!" + error);
			}
		);

		this.requestQueue.add(request);
		return this.shoplistEntries;
	}

	@Override
	public List<Item> getListReference() {
		return this.shoplistEntries;
	}
}
