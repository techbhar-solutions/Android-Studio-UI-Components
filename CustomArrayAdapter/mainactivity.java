package com.example.myapp;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


     ListView listView = findViewById(R.id.listView);

    ArrayList<User> users = new ArrayList<>();
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));
        users.add(new User("Amit", "amit@mail.com", R.drawable.a1));
        users.add(new User("Neha", "neha@mail.com", R.drawable.a2));
        users.add(new User("Suman", "suman@mail.com", R.drawable.a3));

        UserAdapter adapter = new UserAdapter(this, users);
        listView.setAdapter(adapter);
    }
}
