package com.example.cykashoppinglist.entity;

import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class LogEntry implements Item {
	private Long id;
	private String type;
	private String source;
	private String sourceIp;
	private Date date;

	@Override
	public String getName() {
		return source + "(" + type + ")";
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Boolean hasDelete() {
		return false;
	}

	@Override
	public View.OnClickListener getOnClickListener() {
		return v -> {
			Toast.makeText(v.getContext(), sourceIp, Toast.LENGTH_SHORT).show();
		};
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
