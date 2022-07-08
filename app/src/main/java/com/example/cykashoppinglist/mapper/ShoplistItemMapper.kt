package com.example.cykashoppinglist.mapper

import com.example.cykashoppinglist.entity.ShoplistItem
import org.json.JSONException
import org.json.JSONObject

object ShoplistItemMapper {
	@Throws(JSONException::class)
	fun toEntity(jsonObject: JSONObject): ShoplistItem {
		val item = ShoplistItem()
		item.name = jsonObject.getString("content")
		return item
	}
}