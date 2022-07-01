package com.example.cykashoppinglist.entity;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;

import com.example.cykashoppinglist.MainActivity;

import java.util.Date;

public class TodoEntry implements Item {
	private Long id;
	private String content;
	private Date date;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public View.OnClickListener getOnClickListener() {
		return v -> {
			ClipboardManager clipboard = (ClipboardManager) MainActivity.mainContext.getSystemService(Context.CLIPBOARD_SERVICE);
		};
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getName() {
		return content;
	}

	@Override
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
