package com.example.cykashoppinglist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.cykashoppinglist.adapter.Adapter;
import com.example.cykashoppinglist.entity.GenericItem;
import com.example.cykashoppinglist.service.ServiceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	Adapter adapter;
	ServiceManager serviceManager;
	// todo How to do better?
	public static DateFormat dateFormat;
	public static RequestQueue requestQueue;
	public static Context mainContext;

	TextView shoppingListText, todoListText, refreshText, logText;
	EditText textInput;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dateFormat = new SimpleDateFormat(this.getResources().getString(R.string.dateFormat));
		mainContext = this;
		requestQueue = Volley.newRequestQueue(mainContext);

		recyclerView = findViewById(R.id.recyclerView);
		setupTitleTextButtons();
		setupInputText();

		serviceManager = new ServiceManager();
		adapter = new Adapter(mainContext, serviceManager.getListReference());
		serviceManager.setAdapter(adapter);

		recyclerView.setLayoutManager(new LinearLayoutManager(mainContext));
		recyclerView.setAdapter(adapter);
		serviceManager.getAll();
	}

	private void setupTitleTextButtons() {
		shoppingListText = findViewById(R.id.shoppingListText);
		todoListText = findViewById(R.id.todoListText);
		logText = findViewById(R.id.logText);

		refreshText = findViewById(R.id.refreshText);

		View.OnClickListener listener = v -> {
			String service = ((TextView) v).getText().toString();
			serviceManager.switchService(service);
		};

		refreshText.setOnClickListener(v -> serviceManager.getAll());

		logText.setOnClickListener(listener);
		shoppingListText.setOnClickListener(listener);
		todoListText.setOnClickListener(listener);
	}

	private void setupInputText() {
		textInput = findViewById(R.id.inputText);
		textInput.setOnEditorActionListener((v, actionId, event) -> {
			GenericItem item = new GenericItem(textInput.getText().toString());
			serviceManager.postItem(item);
			textInput.setText("");
			return true;
		});
	}
}