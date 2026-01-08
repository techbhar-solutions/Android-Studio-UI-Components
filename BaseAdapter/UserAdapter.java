package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Context context;
    List<User> users;
    LayoutInflater inflater;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_list_items,parent,false);
        }
        ImageView img = convertView.findViewById(R.id.profileImage);
        TextView name = convertView.findViewById(R.id.nameText);

        User user = users.get(position);
        name.setText(user.name);
        img.setImageResource(user.image);

        return convertView;
    }
}
