package com.example.myapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;


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

    List<Map<String, String>> userList = new ArrayList<>();

   Map<String , String> user1 = new HashMap<>();
   user1.put("name", "Mohit Goyal");
   user1.put("email", "mohitgoyal@gmail.com");
   userList.add(user1);
   Map<String , String> user2 = new HashMap<>();
   user2.put("name", "Sahil Goutom");
   user2.put("email","sahilgoutom@gmail.com");
   userList.add(user2);
   Map<String , String> user3 = new HashMap<>();
   user3.put("name", "Suraj");
   user3.put("email","surajkumarmehto01@gmail.com");
   userList.add(user3);


SimpleAdapter adapter = new SimpleAdapter(this, userList, R.layout.activity_list_items,
        new String[]{"name", "email"}, new int[]{R.id.nameView, R.id.emailView});

    listView.setAdapter(adapter);









    }
}
