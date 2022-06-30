package com.example.cykashoppinglist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.service.ServiceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	Adapter adapter;
	ServiceManager serviceManager;
	// todo How to do better?
	public static DateFormat dateFormat;

	TextView shoppingListText, todoListText;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dateFormat = new SimpleDateFormat(this.getResources().getString(R.string.dateFormat));

		recyclerView = findViewById(R.id.recyclerView);
		setupTitleTextButtons();

		serviceManager = new ServiceManager(this);
		adapter = new Adapter(this, serviceManager.getListReference());
		serviceManager.setAdapter(adapter);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		serviceManager.getAll();
	}

	private void setupTitleTextButtons() {
		shoppingListText = findViewById(R.id.shoppingListText);
		todoListText = findViewById(R.id.todoListText);

		View.OnClickListener listener = v -> {
			String service = ((TextView) v).getText().toString();
			serviceManager.switchService(service);
		};

		shoppingListText.setOnClickListener(listener);
		todoListText.setOnClickListener(listener);
	}
}