package com.example.cykashoppinglist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.cykashoppinglist.adapter.Adapter
import com.example.cykashoppinglist.entity.GenericItem
import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.service.ServiceManager
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
	var recyclerView: RecyclerView? = null
	var adapter: Adapter? = null
	var serviceManager: ServiceManager? = null
	var shoppingListText: TextView? = null
	var todoListText: TextView? = null
	var refreshText: TextView? = null
	var logText: TextView? = null
	var textInput: EditText? = null

	@SuppressLint("SimpleDateFormat")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		dateFormat = SimpleDateFormat(this.resources.getString(R.string.dateFormat))
		mainContext = this
		requestQueue = Volley.newRequestQueue(mainContext)
		recyclerView = findViewById(R.id.recyclerView)
		setupTitleTextButtons()
		setupInputText()
		serviceManager = ServiceManager()
		adapter = Adapter(mainContext, serviceManager!!.getListReference() as List<Item>)
		serviceManager!!.setAdapter(adapter)
		recyclerView!!.setLayoutManager(LinearLayoutManager(mainContext))
		recyclerView!!.setAdapter(adapter)
		serviceManager!!.getAll()
	}

	private fun setupTitleTextButtons() {
		shoppingListText = findViewById(R.id.shoppingListText)
		todoListText = findViewById(R.id.todoListText)
		logText = findViewById(R.id.logText)
		refreshText = findViewById(R.id.refreshText)
		val listener = View.OnClickListener { v: View ->
			val service = (v as TextView).text.toString()
			serviceManager!!.switchService(service)
		}
		refreshText!!.setOnClickListener { serviceManager!!.getAll() }
		logText!!.setOnClickListener(listener)
		shoppingListText!!.setOnClickListener(listener)
		todoListText!!.setOnClickListener(listener)
	}

	private fun setupInputText() {
		textInput = findViewById(R.id.inputText)
		textInput!!.setOnEditorActionListener { _: TextView?, _: Int, _: KeyEvent? ->
			val item = GenericItem(textInput!!.getText().toString())
			serviceManager!!.postItem(item)
			textInput!!.setText("")
			true
		}
	}

	companion object {
		// todo How to do better?
		var dateFormat: DateFormat? = null
		var requestQueue: RequestQueue? = null
		var mainContext: Context? = null
	}
}