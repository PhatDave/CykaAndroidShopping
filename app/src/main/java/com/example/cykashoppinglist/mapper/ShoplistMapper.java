package com.example.cykashoppinglist.mapper;

import com.example.cykashoppinglist.entity.ShoplistEntry;
import com.example.cykashoppinglist.util.DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShoplistMapper {
	public static ShoplistEntry map(JSONObject jsonObject) throws JSONException {
		ShoplistEntry entry = new ShoplistEntry();
		entry.setDate(DateParser.parse(jsonObject.getString("date")));
		entry.setId(jsonObject.getLong("id"));
		entry.setItem(ShoplistItemMapper.map(jsonObject));
		return entry;
	}

	public static List<ShoplistEntry> map(JSONArray jsonArray) {
		return new ArrayList<>();
	}
}
