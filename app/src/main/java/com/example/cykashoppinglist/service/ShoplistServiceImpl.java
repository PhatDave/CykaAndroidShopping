package com.example.cykashoppinglist.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.mapper.ShoplistMapper;

import org.json.JSONException;

import java.util.List;

public class ShoplistServiceImpl implements RestService {
	private final RequestQueue requestQueue;
	private final String url;
	private Adapter adapter;
	private final List<Item> shoplistEntries;

	public ShoplistServiceImpl(Context context, List<Item> items) {
		this.requestQueue = Volley.newRequestQueue(context);
//		todo maybe move these to like strings or some other constant file
		this.url = context.getResources().getString(R.string.host) + context.getResources().getString(R.string.shoppingList);
		this.shoplistEntries = items;
	}

	@Override
	public List<Item> getAll() {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
			response -> {
			// todo maybe generify this with reflection
				this.handleResponse(ShoplistMapper.toEntity(response));
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

	@Override
	public Item postItem(Item item) {
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, ShoplistMapper.toJson((ShoplistEntry) item),
			response -> {
				try {
					Item returnItem = ShoplistMapper.toEntity(response);
					this.shoplistEntries.add(returnItem);
					this.adapter.notifyItemInserted(this.shoplistEntries.size() - 1);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			},
			error -> {
				System.out.println("Uhoh!" + error);
			}
		);

		this.requestQueue.add(request);
		return null;
	}

	@SuppressLint("NotifyDataSetChanged")
	@Override
	public void handleResponse(List<Item> response) {
		// This must be done to preserve the reference to this.shoplistEntries
		this.shoplistEntries.removeIf(entry -> true);
		this.shoplistEntries.addAll(response);
		this.adapter.notifyDataSetChanged();
	}

	@Override
	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}
}
