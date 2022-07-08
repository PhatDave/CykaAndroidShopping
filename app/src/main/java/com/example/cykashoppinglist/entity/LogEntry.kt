package com.example.cykashoppinglist.entity

import android.view.View
import android.widget.Toast
import java.util.*

class LogEntry : Item {
	override var id: Long? = null
	var type: String? = null
	var source: String? = null
	var sourceIp: String? = null
	override var date: Date? = null
	override val name: String?
		get() = "$source($type)"

	override fun hasDelete(): Boolean {
		return false
	}

	override val onClickListener: View.OnClickListener?
		get() = View.OnClickListener { v: View ->
			Toast.makeText(
				v.context,
				sourceIp,
				Toast.LENGTH_SHORT
			).show()
		}
}