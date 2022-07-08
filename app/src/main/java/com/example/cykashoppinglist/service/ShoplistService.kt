package com.example.cykashoppinglist.service

import com.example.cykashoppinglist.MainActivity
import com.example.cykashoppinglist.R
import com.example.cykashoppinglist.entity.GenericItem
import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.mapper.ShoplistMapper

class ShoplistService : RestService {
	private val url: String

	override fun getAll() {
		doGet(url, ShoplistMapper)
	}

	override fun postItem(item: GenericItem?) {
		doPost(item!!, url, ShoplistMapper)
	}

	override fun deleteItem(item: Item?) {
		doDelete(item!!, url)
	}

	init {
		url = MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.host) + MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.shoppingList)
	}
}