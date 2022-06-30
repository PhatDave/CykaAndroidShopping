package com.example.cykashoppinglist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.service.RestService;
import com.example.cykashoppinglist.service.ShoplistServiceImpl;

public class MainActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.recyclerView);

		RestService restService = new ShoplistServiceImpl(this);
		restService.getAll();

		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		adapter = new Adapter(getApplicationContext(), restService.getListReference());
		recyclerView.setAdapter(adapter);
	}
}