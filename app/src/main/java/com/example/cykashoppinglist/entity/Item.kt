package com.example.cykashoppinglist.entity

import android.view.View
import com.example.cykashoppinglist.MainActivity
import java.util.*

interface Item {
	val name: String?
	val date: Date?
	val id: Long?
	fun hasDelete(): Boolean
	val onClickListener: View.OnClickListener?
	val dateString: String?
		get() = if (date == null) "" else MainActivity.dateFormat?.format(date)
}