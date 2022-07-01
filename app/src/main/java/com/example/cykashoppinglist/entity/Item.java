package com.example.cykashoppinglist.entity;

import com.example.cykashoppinglist.MainActivity;

import java.util.Date;

public interface Item {
	String getName();
	Date getDate();
	Long getId();
	default String getDateString() {
		return this.getDate() == null ? "" : MainActivity.dateFormat.format(this.getDate());
	}
}
