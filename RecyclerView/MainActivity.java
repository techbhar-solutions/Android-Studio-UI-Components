package com.example.myapp;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        RecyclerView recyclerView = findViewById(R.id.recycleview);
        List<String> names = Arrays.asList("Mohit Goyal","Rohit Mehra","Ravi Kumar","Sahil Goutom","Prince Sharma","Virender Singh","Vikas Kumar","Vikas Kumar","Vikas Kumar",
                "Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar","Vikas Kumar",
                "Mohit","Mohit","Mphit","Mohit","mohit");
        UserAdapter adapter = new UserAdapter(names);


       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}
