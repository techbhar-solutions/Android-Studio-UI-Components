package com.example.uicomponents;


import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
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


        TextClock textClock = findViewById(R.id.textClock);
        textClock.setFormat12Hour("hh:mm:ss a");
        textClock.setFormat24Hour("HH:mm:ss");

        // For different time zone
        textClock.setTimeZone("GMT+05:30");

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide);
        textClock.startAnimation(anim);



    }
}
