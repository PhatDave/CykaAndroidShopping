package com.example.cykashoppinglist.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {
	public static Date parse(String input) {
		String[] ts = input.split("T");
		String ymd = ts[0];
		String hms = ts[1];

		int year = Integer.parseInt(ymd.split("-")[0]);
		int month = Integer.parseInt(ymd.split("-")[1]);
		int day = Integer.parseInt(ymd.split("-")[2]);
		int hour = Integer.parseInt(hms.split(":")[0]);
		int minute = Integer.parseInt(hms.split(":")[1]);
		int second = Integer.parseInt(hms.split(":")[2].split("\\.")[0]);
		return new Date(year, month, day, hour, minute, second);
	}
}
