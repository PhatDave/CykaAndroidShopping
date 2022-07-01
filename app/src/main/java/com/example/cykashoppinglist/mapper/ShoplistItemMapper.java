package com.example.cykashoppinglist.mapper;

import com.example.cykashoppinglist.entity.ShoplistItem;

import org.json.JSONException;
import org.json.JSONObject;

public class ShoplistItemMapper {
	public static ShoplistItem toEntity(JSONObject jsonObject) throws JSONException {
		ShoplistItem item = new ShoplistItem();
		item.setName(jsonObject.getString("content"));
		return item;
	}
}
