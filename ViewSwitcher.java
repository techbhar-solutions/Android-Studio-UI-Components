package com.example.myapp;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewSwitcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // ✅ Correct IDs
        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.loginregis);

        // Button animation
        loginBtn.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.scale)
        );
        registerBtn.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.scale)
        );

        ViewSwitcher viewSwitcher = findViewById(R.id.viewSwitcher);

        findViewById(R.id.loginregis).setOnClickListener(v ->
                viewSwitcher.showNext());

        findViewById(R.id.backToLogin).setOnClickListener(v ->
                viewSwitcher.showPrevious());
    }
}
