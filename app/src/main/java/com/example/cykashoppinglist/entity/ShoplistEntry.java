package com.example.cykashoppinglist.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoplistEntry {
	private Long id;
	private ShoplistItem item;
	private Date date;
}
