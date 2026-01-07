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


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


     String [] names = {"Mohit Goyal","Sahil Goutam","Prince Sharma","Virender Singh","Suraj Mehto"};
     ListView listView = findViewById(R.id.listView);
      //  ArrayAdapter banao
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
     // Adaptor ko Set karna
        listView.setAdapter(adapter);

        // item click Listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
         String selectedname = names[position];
            Toast.makeText(this, "Selected Name: " + selectedname, Toast.LENGTH_SHORT).show();
        });

    }
}
