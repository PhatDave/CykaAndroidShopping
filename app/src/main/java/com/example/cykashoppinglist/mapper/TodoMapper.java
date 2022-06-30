package com.example.cykashoppinglist.mapper;

import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.TodoEntry;
import com.example.cykashoppinglist.util.DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodoMapper {
	public static Item map(JSONObject jsonObject) throws JSONException {
		TodoEntry entry = new TodoEntry();
		entry.setId(jsonObject.getLong("id"));
		entry.setDate(DateParser.parse(jsonObject.getString("date")));
		entry.setContent(jsonObject.getString("content"));
		return entry;
	}

	public static List<Item> map(JSONArray jsonArray) {
		List<Item> entries = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				entries.add(TodoMapper.map(jsonObject));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return entries;
	}
}
