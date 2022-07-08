package com.example.cykashoppinglist.mapper

import com.example.cykashoppinglist.entity.GenericItem
import org.json.JSONException
import org.json.JSONObject

object GenericMapper {
	fun toJson(item: GenericItem): JSONObject? {
		try {
			val jsonObject = JSONObject()
			jsonObject.put("content", item.name)
			return jsonObject
		} catch (e: JSONException) {
			e.printStackTrace()
		}
		return null
	}
}