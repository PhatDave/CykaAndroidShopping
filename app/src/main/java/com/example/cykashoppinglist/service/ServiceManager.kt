package com.example.cykashoppinglist.service

import android.annotation.SuppressLint
import com.example.cykashoppinglist.MainActivity
import com.example.cykashoppinglist.R
import com.example.cykashoppinglist.adapter.Adapter
import com.example.cykashoppinglist.entity.GenericItem
import com.example.cykashoppinglist.entity.Item

class ServiceManager {
	private val items: MutableList<Item?>
	private var adapter: Adapter? = null
	private val services: Map<String, RestService>
	private var activeService: RestService?
	companion object {
		lateinit var instance: ServiceManager
	}

	fun getAll() {
		this.activeService!!.getAll()
	}

	fun getListReference() : List<Item?> {
		return this.items
	}

	@SuppressLint("NotifyDataSetChanged")
	fun getCallback(response: List<Item?>?) {
		// There's probably a better way *not* removing all the entries but I can't be bothered
		items.removeIf { true }
		items.addAll(response!!)
		adapter!!.notifyDataSetChanged()
	}

	fun postItem(item: GenericItem?) {
		activeService!!.postItem(item)
	}

	fun postCallback(item: Item?) {
		items.add(item)
		adapter!!.notifyItemInserted(items.size - 1)
	}

	fun delete(position: Int?) {
		try {
			activeService!!.deleteItem(items[position!!])
		} catch (ignored: IndexOutOfBoundsException) {
		}
	}

	fun deleteCallback(id: Long?) {
		var pos = -1
		var foundItem: Item? = null
		for (item in items) {
			if (item!!.id === id) {
				pos = items.indexOf(item)
				foundItem = item
				break
			}
		}
		if (pos == -1) {
			return
		}
		items.remove(foundItem)
		adapter!!.notifyItemRemoved(pos)
	}

	fun setAdapter(adapter: Adapter?) {
		this.adapter = adapter
	}

	// this is bad but I don't think the application ever should expand to more than 2 services
	fun switchService(serviceName: String) {
		activeService = services[serviceName]
	}

	init {
		instance = this
		items = ArrayList()
		val shoppingListService: RestService = ShoplistService()
		val todoService: RestService = TodoService()
		val logService: RestService = LogService()
		val shoppingListName: String = MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.shoppingListServiceName)
		val todoListName: String = MainActivity.Companion.mainContext!!.getResources()
			.getString(R.string.todoListServiceName)
		val loggerName: String =
			MainActivity.Companion.mainContext!!.getResources().getString(R.string.logServiceName)
		services = java.util.Map.of(
			shoppingListName, shoppingListService,
			todoListName, todoService,
			loggerName, logService
		)
		activeService = shoppingListService
	}
}