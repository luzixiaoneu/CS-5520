package com.example.cs_5520_a1;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs_5520_a1.entity.LinkElement;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<LinkElement> elementList;
    private OnNoteListener onNoteListener;

    public RecyclerAdapter(List<LinkElement> elementList, OnNoteListener onNoteListener) {
        this.elementList = elementList;
        this.onNoteListener = onNoteListener;
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_controller_row, parent, false);
        return new MyViewHolder(itemView, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = this.elementList.get(position).getName();
        String URL = this.elementList.get(position).getURL();
        holder.name.setText(name);
        holder.url.setText(URL);

    }

    @Override
    public int getItemCount() {
        return this.elementList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView url;
        OnNoteListener onNoteListener;
        public MyViewHolder(final View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            url = itemView.findViewById(R.id.url);
            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener {
         void onNoteClick(int position);
    }

}
