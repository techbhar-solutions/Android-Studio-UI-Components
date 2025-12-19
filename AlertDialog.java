package com.example.uicomponents;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextClock;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button alertshow = findViewById(R.id.button);
        EditText datePickerField = findViewById(R.id.editTextText);
        datePickerField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(MainActivity.this,
                                (view, y, m, d) -> {
                                    String date = d + "/" + (m + 1) + "/" + y;
                                    datePickerField.setText(date);
                                },
                                year,
                                month,
                                day);
                datePickerDialog.show();


            }

            });

           Button clearButton = findViewById(R.id.buttonclear);


           clearButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   datePickerField.setText("Date Of Birth");
               }
           });

        alertshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setIcon(R.drawable.bus_alert);
                alert.setCancelable(false);
                alert.setTitle("Are You Sure?");
                alert.setMessage("Do you want to continue?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, " You Click Yes", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, " You Click No", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
               // Toast.makeText(MainActivity.this, "Alert Dialog", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
