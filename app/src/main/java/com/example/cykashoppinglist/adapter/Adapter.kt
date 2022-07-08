package com.example.cykashoppinglist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cykashoppinglist.R
import com.example.cykashoppinglist.entity.Item
import com.example.cykashoppinglist.service.ServiceManager

class Adapter(context: Context?, items: List<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
	var inflater: LayoutInflater
	var items: List<Item>
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = inflater.inflate(R.layout.item, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = items[position]
		holder.itemName.text = item.name
		holder.itemDate.text = item.dateString
		holder.itemView.setOnClickListener(item.onClickListener)
		if (!item.hasDelete()) {
			holder.itemView.findViewById<View>(R.id.deleteButton).visibility = View.GONE
		} else {
			holder.itemView.findViewById<View>(R.id.deleteButton).visibility =
				View.VISIBLE
		}
	}

	override fun getItemCount(): Int {
		return items.size
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var itemName: TextView
		var itemDate: TextView
		var itemButton: Button

		init {
			itemName = itemView.findViewById(R.id.itemName)
			itemDate = itemView.findViewById(R.id.itemDate)
			itemButton = itemView.findViewById(R.id.deleteButton)
			itemButton.setOnClickListener {
				ServiceManager.instance.delete(
					adapterPosition
				)
			}
		}
	}

	init {
		inflater = LayoutInflater.from(context)
		this.items = items
	}
}