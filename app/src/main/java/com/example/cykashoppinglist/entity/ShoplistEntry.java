package com.example.cykashoppinglist.entity;

import java.util.Date;

public class ShoplistEntry {
	private Long id;
	private ShoplistItem item;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShoplistItem getItem() {
		return item;
	}

	public void setItem(ShoplistItem item) {
		this.item = item;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
