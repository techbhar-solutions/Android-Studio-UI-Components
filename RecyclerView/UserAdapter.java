package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<String> list;

    public UserAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_user_row, parent, false);
        return new UserViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
        }

    }
}
