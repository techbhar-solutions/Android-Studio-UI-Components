package com.example.myapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);




  ListView listView = findViewById(R.id.listView);

  List<User> users = new ArrayList<>();
  users.add(new User("Suraj",R.drawable.a1));
  users.add(new User("Ankit", R.drawable.p2));
  users.add(new User("Sahil", R.drawable.p3));
  users.add(new User("Mohit", R.drawable.a2));

  UserAdapter adapter = new UserAdapter(this,users);
  listView.setAdapter(adapter);



        listView.setOnItemClickListener((parent, view, position, id) -> {
            User user = users.get(position);
            Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show();
        });


    }
}
