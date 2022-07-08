package com.example.cykashoppinglist.service

import com.example.cykashoppinglist.MainActivity
import com.example.cykashoppinglist.R
import com.example.cykashoppinglist.entity.GenericItem
import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.mapper.TodoMapper

class TodoService : RestService {
	private val url: String

	override fun getAll() {
		doGet(url, TodoMapper)
	}

	override fun postItem(item: GenericItem?) {
		doPost(item!!, url, TodoMapper)
	}

	override fun deleteItem(item: Item?) {
		doDelete(item!!, url)
	}

	init {
		url = MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.host) + MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.todoList)
	}
}