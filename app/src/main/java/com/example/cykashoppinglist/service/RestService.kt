package com.example.cykashoppinglist.service

import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.cykashoppinglist.MainActivity
import com.example.cykashoppinglist.entity.GenericItem
import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.mapper.GenericMapper
import com.example.cykashoppinglist.mapper.Mapper
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.InvocationTargetException

interface RestService {
	fun getAll()
	fun postItem(item: GenericItem?)
	fun deleteItem(item: Item?)

	companion object {
		lateinit var mapper: Class<*>
	}

	fun doGet(url: String?, mapper: Mapper) {
		val request = JsonArrayRequest(
			Request.Method.GET, url, null,
			{ response: JSONArray? ->
				try {
					val returnItems = mapper.toEntity(response!!)
					ServiceManager.instance.getCallback(returnItems)
				} catch (e: IllegalAccessException) {
					e.printStackTrace()
				} catch (e: InvocationTargetException) {
					e.printStackTrace()
				} catch (e: NoSuchMethodException) {
					e.printStackTrace()
				}
			}
		) { error: VolleyError -> println("Uhoh!$error") }
		MainActivity.requestQueue!!.add(request)
	}

	fun doPost(item: GenericItem, url: String?, mapper: Mapper) {
		val request = JsonObjectRequest(
			Request.Method.POST, url, GenericMapper.toJson(item),
			{ response: JSONObject? ->
				try {
					val returnItem = mapper.toEntity(response!!)
					ServiceManager.instance.postCallback(returnItem)
					// What a nice error stew
				} catch (e: NoSuchMethodException) {
					e.printStackTrace()
				} catch (e: InvocationTargetException) {
					e.printStackTrace()
				} catch (e: IllegalAccessException) {
					e.printStackTrace()
				}
			}
		) { error: VolleyError -> println("Uhoh!$error") }
		MainActivity.requestQueue!!.add(request)
	}

	fun doDelete(item: Item, url: String) {
		val itemId = item.id
		val request = StringRequest(
			Request.Method.DELETE, url + "/" + itemId.toString(),
			{ ServiceManager.instance.deleteCallback(itemId) }
		) { error: VolleyError -> println("Uhoh!$error") }
		MainActivity.requestQueue!!.add(request)
	}
}