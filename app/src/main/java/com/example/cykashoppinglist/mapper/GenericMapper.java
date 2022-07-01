package com.example.cykashoppinglist.mapper;

import com.example.cykashoppinglist.entity.GenericItem;

import org.json.JSONException;
import org.json.JSONObject;

public class GenericMapper {
	public static JSONObject toJson(GenericItem item) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("content", item.getName());
			return jsonObject;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
