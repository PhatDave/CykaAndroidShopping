package com.example.cykashoppinglist.mapper

import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.entity.TodoEntry
import com.example.cykashoppinglist.util.DateParser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object TodoMapper : Mapper {
	@Throws(JSONException::class)
	override fun toEntity(jsonObject: JSONObject): Item {
		val entry = TodoEntry()
		entry.id = jsonObject.getLong("id")
		entry.date = DateParser.parse(jsonObject.getString("date"))
		entry.name = jsonObject.getString("content")
		return entry
	}

	override fun toEntity(jsonArray: JSONArray): List<Item> {
		val entries: MutableList<Item> = ArrayList()
		for (i in 0 until jsonArray.length()) {
			try {
				val jsonObject = jsonArray.getJSONObject(i)
				entries.add(toEntity(jsonObject))
			} catch (e: JSONException) {
				e.printStackTrace()
			}
		}
		return entries
	}
}