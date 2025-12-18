package com.example.uicomponents;


import android.media.Rating;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.TextClock;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        RatingBar ratingBar = findViewById(R.id.ratingBar);

       float rating = ratingBar.getRating();
        ratingBar.setOnRatingBarChangeListener((bar, rating1, fromUser) -> {
            if (rating<=3) {
                Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "We will improve!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
