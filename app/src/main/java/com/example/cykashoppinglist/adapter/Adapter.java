package com.example.cykashoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cykashoppinglist.R;
import com.example.cykashoppinglist.entity.Item;
import com.example.cykashoppinglist.service.ServiceManager;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
	LayoutInflater inflater;
	List<Item> items;

	public Adapter(Context context, List<Item> items) {
		this.inflater = LayoutInflater.from(context);
		this.items = items;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Item item = items.get(position);
		holder.itemName.setText(item.getName());
		holder.itemDate.setText(item.getDateString());
		holder.itemView.setOnClickListener(item.getOnClickListener());
		if (!item.hasDelete()) {
			holder.itemView.findViewById(R.id.deleteButton).setVisibility(View.GONE);
		} else {
			holder.itemView.findViewById(R.id.deleteButton).setVisibility(View.VISIBLE);
		}
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView itemName, itemDate;
		Button itemButton;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			itemName = itemView.findViewById(R.id.itemName);
			itemDate = itemView.findViewById(R.id.itemDate);
			itemButton = itemView.findViewById(R.id.deleteButton);

			itemButton.setOnClickListener(v -> ServiceManager.getInstance().delete(getAdapterPosition()));
		}
	}
}