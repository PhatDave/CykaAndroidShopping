package com.example.cykashoppinglist.mapper;

import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.util.DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShoplistMapper {
	public static Item toEntity(JSONObject jsonObject) throws JSONException {
		ShoplistEntry entry = new ShoplistEntry();
		entry.setDate(DateParser.parse(jsonObject.getString("date")));
		entry.setId(jsonObject.getLong("id"));
		entry.setItem(ShoplistItemMapper.toEntity(jsonObject));
		return entry;
	}

	public static List<Item> toEntity(JSONArray jsonArray) {
		List<Item> entries = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				entries.add(ShoplistMapper.toEntity(jsonObject));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return entries;
	}
}
