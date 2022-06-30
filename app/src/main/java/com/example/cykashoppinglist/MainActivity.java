package com.example.cykashoppinglist;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.service.RestService;
import com.example.cykashoppinglist.service.ShoplistServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	Adapter adapter;
	// todo How to do better?
	public static DateFormat dateFormat;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dateFormat = new SimpleDateFormat(this.getResources().getString(R.string.dateFormat));

		recyclerView = findViewById(R.id.recyclerView);

		List<Item> items = new ArrayList<>();
		RestService restService = new ShoplistServiceImpl(this, items);
		adapter = new Adapter(this, items);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		restService.setAdapter(adapter);
		restService.getAll();
	}
}