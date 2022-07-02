package com.example.cykashoppinglist.entity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

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
	public Boolean hasDelete() {
		return true;
	}

	@Override
	public View.OnClickListener getOnClickListener() {
		return v -> {
			ClipboardManager clipboard = (ClipboardManager) MainActivity.mainContext.getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.clearPrimaryClip();
			clipboard.setPrimaryClip(new ClipData(content, new String[]{"text/plain"}, new ClipData.Item(content)));
			Toast.makeText(v.getContext(), "Copied entry to clipboard!", Toast.LENGTH_SHORT).show();
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
