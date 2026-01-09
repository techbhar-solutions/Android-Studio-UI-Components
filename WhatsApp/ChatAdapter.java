package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
     ArrayList<ChatModel> chatList;
    public ChatAdapter(ArrayList<ChatModel> chatList) {
        this.chatList = chatList;
    }


   @NonNull
   @Override
   public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_user_row, parent, false);
       return new ChatAdapter.ViewHolder(view);
   }

   @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        ChatModel model = chatList.get(position);
        holder.txtName.setText(model.sender);
       // Use txtSubtitle as it exists in the layout
       holder.txtSubtitle.setText(model.message);
       // There's no view for time in the layout, so we can't set it.
       // holder.txtTime.setText(model.time);
   }

   @Override
    public int getItemCount() {
       return chatList.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
        // Correct the TextView variable names to match the layout
        TextView txtName,txtSubtitle;
        ImageView imgProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            // Find the view by its correct ID
            txtSubtitle = itemView.findViewById(R.id.txtMessage);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
   }
}
