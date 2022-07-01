package com.example.cykashoppinglist.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {
	@SuppressLint("SimpleDateFormat")
	public static Date parse(String input) {
		input = input.replace("T", " ");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static String parse(Date input) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String output = format.format(input);
		output = output.replace(" ", "T");
		return output;
	}
}
