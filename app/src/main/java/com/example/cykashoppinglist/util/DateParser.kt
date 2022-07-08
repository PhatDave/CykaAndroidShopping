package com.example.cykashoppinglist.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateParser {
	@SuppressLint("SimpleDateFormat")
	fun parse(input: String): Date {
		var input = input
		input = input.replace("T", " ")
		val format: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		return try {
			format.parse(input)
		} catch (e: ParseException) {
			e.printStackTrace()
			Date()
		}
	}

	fun parse(input: Date?): String {
		val format: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		var output = format.format(input)
		output = output.replace(" ", "T")
		return output
	}
}