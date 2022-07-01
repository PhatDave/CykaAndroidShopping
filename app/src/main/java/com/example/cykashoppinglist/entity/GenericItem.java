package com.example.cykashoppinglist.entity;

import java.util.Date;

public class GenericItem implements Item {
	private final String content;

	public GenericItem(String content) {
		this.content = content;
	}

	@Override
	public String getName() {
		return this.content;
	}

	@Override
	public Date getDate() {
		return new Date();
	}
}
