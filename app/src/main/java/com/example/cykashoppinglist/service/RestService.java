package com.example.cykashoppinglist.service;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.TodoEntry;
import com.example.cykashoppinglist.mapper.GenericMapper;
import com.example.cykashoppinglist.mapper.ShoplistMapper;

import org.json.JSONException;

import java.util.List;

public interface RestService {
	void getAll();
	void handleResponse(List<Item> response);
	void setAdapter(Adapter adapter);
	void postItem(GenericItem item);

	default void doPost(GenericItem item, String url) {
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, GenericMapper.toJson(item),
				response -> {
					try {
						Item returnItem = ShoplistMapper.toEntity(response);
						ServiceManager.getInstance().addItem(returnItem);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				},
				error -> {
					System.out.println("Uhoh!" + error);
				}
		);

		MainActivity.requestQueue.add(request);
	}
}
