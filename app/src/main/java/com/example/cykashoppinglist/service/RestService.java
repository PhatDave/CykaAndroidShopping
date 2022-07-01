package com.example.cykashoppinglist.service;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.cykashoppinglist.MainActivity;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.mapper.GenericMapper;
import com.example.cykashoppinglist.mapper.ShoplistMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface RestService {
	void getAll();
	void postItem(GenericItem item);
	void deleteItem(Item item);

	default void doGet(String url, Class<?> mapper) {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
				response -> {
					try {
						List<Item> returnItems = (List<Item>) mapper.getMethod("toEntity", JSONArray.class).invoke(null, response);
						ServiceManager.getInstance().getCallback(returnItems);
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						e.printStackTrace();
					}
				},
				error -> {
					System.out.println("Uhoh!" + error);
				}
		);

		MainActivity.requestQueue.add(request);
	}

	default void doPost(GenericItem item, String url, Class<?> mapper) {
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, GenericMapper.toJson(item),
				response -> {
					try {
						Item returnItem = (Item) mapper.getMethod("toEntity", JSONObject.class).invoke(null, response);
						ServiceManager.getInstance().postCallback(returnItem);
						// What a nice error stew
					} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
						e.printStackTrace();
					}
				},
				error -> {
					System.out.println("Uhoh!" + error);
				}
		);

		MainActivity.requestQueue.add(request);
	}

	default void doDelete(Item item, String url) {
		Long itemId = item.getId();
		StringRequest request = new StringRequest(Request.Method.DELETE, url + "/" + itemId.toString(),
				response -> {
					ServiceManager.getInstance().deleteCallback(itemId);
				},
				error -> {
					System.out.println("Uhoh!" + error);
				}
		);

		MainActivity.requestQueue.add(request);
	}
}
