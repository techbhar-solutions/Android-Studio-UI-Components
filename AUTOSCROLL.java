package com.example.myapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    GestureDetector gestureDetector;
    Handler handler = new Handler();

    int[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.p2};
    int index = 0;
    boolean autoScroll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = findViewById(R.id.imageSwitcher);

        // 1️⃣ Factory (mandatory)
        imageSwitcher.setFactory(() -> {
            ImageView img = new ImageView(this);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(new ImageSwitcher.LayoutParams(
                    ImageSwitcher.LayoutParams.MATCH_PARENT,
                    ImageSwitcher.LayoutParams.MATCH_PARENT));
            return img;
        });

        // 2️⃣ First image
        imageSwitcher.setImageResource(images[index]);

        // 3️⃣ Swipe detector
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,
                                           float velocityX, float velocityY) {

                        autoScroll = false;

                        if (e2.getX() > e1.getX()) {
                            previousImage(); // Right swipe
                        } else {
                            nextImage();     // Left swipe
                        }
                        return true;
                    }
                });

        imageSwitcher.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        // 4️⃣ Auto scroll start
        startAutoScroll();
    }

    // 👉 Next Image
    private void nextImage() {
        index = (index + 1) % images.length;
        imageSwitcher.setImageResource(images[index]);
    }

    // 👈 Previous Image
    private void previousImage() {
        index = (index - 1 + images.length) % images.length;
        imageSwitcher.setImageResource(images[index]);
    }

    // 🔁 Auto Scroll
    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (autoScroll) {
                    nextImage();
                }
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }
}
