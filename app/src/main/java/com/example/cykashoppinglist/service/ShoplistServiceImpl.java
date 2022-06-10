package com.example.cykashoppinglist.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.mapper.ShoplistMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShoplistServiceImpl implements ShoplistService {
	private final RequestQueue requestQueue;
	private final String url;
	private final ArrayList<ShoplistEntry> shoplistEntries;

	public ShoplistServiceImpl(Context context) {
		this.requestQueue = Volley.newRequestQueue(context);
		this.url = "http://178.128.141.50:8080/shoppingList";
		this.shoplistEntries = new ArrayList<>();
	}

	@Override
	public List<ShoplistEntry> getAll() {
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
			response -> {
				for (int i = 0; i < response.length(); i++) {
					try {
						JSONObject jsonObject = response.getJSONObject(i);
						shoplistEntries.add(ShoplistMapper.map(jsonObject));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				System.out.println(response);
			},
			error -> {
				System.out.println("Uhoh!" + error);
			}
		);

		this.requestQueue.add(request);
		return this.shoplistEntries;
	}
}
