package com.example.myapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        SearchView searchkaro = findViewById(R.id.searcView);
        ListView listView = findViewById(R.id.listView);

     names = new ArrayList<>();
     names.add("Mohit");
     names.add("Rohit");
     names.add("Raju");
     names.add("Sahil");
     names.add("Suraj");
     names.add("Prince");
     names.add("Virender");

     adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
     listView.setAdapter(adapter);



     listView.setOnItemClickListener((parent, view, position, id) -> {
         String name = names.get(position);
         Toast.makeText(this, "Hello " + name, Toast.LENGTH_SHORT).show();
     });


     searchkaro.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
         adapter.getFilter().filter(newText);

             return false;
         }
     });


    }
}
