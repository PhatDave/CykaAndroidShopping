package com.example.cykashoppinglist.entity

import android.view.View
import java.util.*

class ShoplistEntry : Item {
	override var id: Long? = null
	var item: ShoplistItem? = null
	override var date: Date? = null
	override fun hasDelete(): Boolean {
		return true
	}

	override val onClickListener: View.OnClickListener?
		get() = View.OnClickListener { }
	override val name: String?
		get() = item!!.name
}