package com.example.cykashoppinglist.mapper

import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.entity.LogEntry
import com.example.cykashoppinglist.util.DateParser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object LogMapper : Mapper {
	@Throws(JSONException::class)
	override fun toEntity(jsonObject: JSONObject): Item {
		val entry = LogEntry()
		entry.id = jsonObject.getLong("id")
		entry.type = jsonObject.getString("type")
		entry.source = jsonObject.getString("source")
		entry.sourceIp = jsonObject.getString("sourceIP")
		entry.date = DateParser.parse(jsonObject.getString("date"))
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