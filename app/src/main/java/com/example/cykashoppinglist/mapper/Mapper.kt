package com.example.cykashoppinglist.mapper

import com.example.cykashoppinglist.entity.Item
import org.json.JSONArray
import org.json.JSONObject

interface Mapper {
	fun toEntity(jsonObject: JSONObject): Item;
	fun toEntity(jsonArray: JSONArray): List<Item>
}