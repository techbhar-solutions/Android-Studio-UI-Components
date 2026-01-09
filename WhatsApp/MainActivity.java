package com.example.myapp;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

     RecyclerView recyclerView = findViewById(R.id.ChatrecyclerView);

        ArrayList<ChatModel> arr = new ArrayList<>();
        arr.add(new ChatModel("Rahul", "Kal milte hain", "Yesterday"));
        arr.add(new ChatModel("Aman", "Ok 👍", "Today"));
        arr.add(new ChatModel("Neha", "Typing…", "Just now"));

     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     recyclerView.setAdapter(new ChatAdapter(arr));

    }
}
