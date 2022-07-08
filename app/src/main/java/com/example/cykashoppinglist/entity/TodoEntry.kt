package com.example.cykashoppinglist.entity

import android.view.View
import java.util.*

class TodoEntry : Item {
	override var id: Long? = null
	override var name: String? = null
		get() = field
		set
	override var date: Date? = null
	override fun hasDelete(): Boolean {
		return true
	}

	override val onClickListener: View.OnClickListener
		get() = View.OnClickListener { }

//	override val onClickListener: View.OnClickListener
//		//	Apparently this is illegal in kotlin...
//		get() = View.OnClickListener { v: View ->
//			val clipboard =
//				MainActivity.mainContext!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//			clipboard.clearPrimaryClip()
//			clipboard.setPrimaryClip(
//				ClipData(
//					name, arrayOf("text/plain"), ClipData.Item(
//						name
//					)
//				)
//			)
//			Toast.makeText(v.context, "Copied entry to clipboard!", Toast.LENGTH_SHORT).show()
//		}
}