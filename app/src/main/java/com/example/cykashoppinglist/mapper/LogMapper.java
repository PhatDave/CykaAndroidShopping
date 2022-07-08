package com.example.cykashoppinglist.mapper;

import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.entity.LogEntry;
import com.example.cykashoppinglist.util.DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogMapper {
	public static Item toEntity(JSONObject jsonObject) throws JSONException {
		LogEntry entry = new LogEntry();
		entry.setId(jsonObject.getLong("id"));
		entry.setType(jsonObject.getString("type"));
		entry.setSource(jsonObject.getString("source"));
		entry.setSourceIp(jsonObject.getString("sourceIP"));
		entry.setDate(DateParser.parse(jsonObject.getString("date")));
		return entry;
	}

	public static List<Item> toEntity(JSONArray jsonArray) {
		List<Item> entries = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				entries.add(LogMapper.toEntity(jsonObject));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return entries;
	}
}
