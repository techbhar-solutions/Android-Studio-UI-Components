package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.p2};
    private int currentPic = 0;

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

        ImageSwitcher imageSwitcher = findViewById(R.id.imageSwitcher);
        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
         @Override
         public View makeView() {
             ImageView imageView = new ImageView(getApplicationContext());
             imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
             return imageView;

         }
        });

        imageSwitcher.setImageResource(images[currentPic]);

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPic-- ;
                if(currentPic < 0){
                    currentPic = images.length - 1;
                }
                imageSwitcher.setImageResource(images[currentPic]);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPic++;
                if (currentPic >= images.length) {
                    currentPic = 0;
                }
                imageSwitcher.setImageResource(images[currentPic]);
            }
        });
    }
}
