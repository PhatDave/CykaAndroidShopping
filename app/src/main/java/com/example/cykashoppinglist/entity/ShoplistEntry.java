package com.example.cykashoppinglist.entity;

import android.view.View;

import java.util.Date;

public class ShoplistEntry implements Item {
	private Long id;
	private ShoplistItem item;
	private Date date;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Boolean hasDelete() {
		return true;
	}

	@Override
	public View.OnClickListener getOnClickListener() {
		return v -> {
		};
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

	@Override
	public String getName() {
		return item.getName();
	}

	@Override
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
