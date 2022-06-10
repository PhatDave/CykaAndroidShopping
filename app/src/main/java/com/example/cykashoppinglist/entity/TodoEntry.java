package com.example.cykashoppinglist.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoEntry {
	private Long id;
	private String content;
	private Date date;
}
