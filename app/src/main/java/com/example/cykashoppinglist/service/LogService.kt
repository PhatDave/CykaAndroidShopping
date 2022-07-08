package com.example.cykashoppinglist.service

import com.example.cykashoppinglist.MainActivity
import com.example.cykashoppinglist.R
import com.example.cykashoppinglist.entity.GenericItem
import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.mapper.LogMapper

class LogService : RestService {
	private val url: String

	override fun getAll() {
		doGet(url, LogMapper)
	}

	override fun postItem(item: GenericItem?) {
		TODO("Needs no implementation")
	}

	override fun deleteItem(item: Item?) {
		TODO("Needs no implementation")
	}

	init {
		url = MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.host) + MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.log) + "/" + MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.logCount)
	}
}