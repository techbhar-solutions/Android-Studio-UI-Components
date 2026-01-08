package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        User user = getItem(position);
        ImageView img = convertView.findViewById(R.id.profileImage);
        TextView name = convertView.findViewById(R.id.nameText);
        TextView email = convertView.findViewById(R.id.emailText);

        if (user != null) {
            img.setImageResource(user.image);
            name.setText(user.name);
            email.setText(user.email);
        }

        return convertView;
    }
}
