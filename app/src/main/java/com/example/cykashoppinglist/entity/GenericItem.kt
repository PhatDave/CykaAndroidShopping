package com.example.cykashoppinglist.entity

import android.view.View
import java.util.*

class GenericItem(private val content: String) : Item {
	override val name: String?
		get() = content
	override val date: Date?
		get() = Date()
	override val id: Long?
		get() = 0L

	override fun hasDelete(): Boolean {
		return true
	}

	// Not implemented nor will be implemented
	override val onClickListener: View.OnClickListener?
		get() =// Not implemented nor will be implemented
			null
}