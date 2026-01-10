package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapp.databinding.ActivityLoginBinding;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private boolean isRegister = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        setupToggle();
        setupSubmit();







    }
    private void setupToggle() {
        binding.toggleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRegister = !isRegister;

                if (isRegister) {
                    binding.titleTxt.setText("Create Account");
                    binding.submitBtn.setText("Register");
                    binding.confirmLayout.setVisibility(View.VISIBLE);
                    binding.toggleTxt.setText("Already have an account? Login");
                } else {
                    binding.titleTxt.setText("Welcome Back");
                    binding.submitBtn.setText("Login");
                    binding.confirmLayout.setVisibility(View.GONE);
                    binding.toggleTxt.setText("Don't have an account? Register");
                }
            }
        });
    }

    private void setupSubmit() {
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailEdt.getText().toString().trim();
                String pass = binding.passEdt.getText().toString().trim();

                if (email.isEmpty()) {
                    binding.emailLayout.setError("Email required");
                    return;
                }

                if (pass.length() < 6) {
                    binding.passLayout.setError("Min 6 characters");
                    return;
                }

                if (isRegister) {
                    String confirm = binding.confirmEdt.getText().toString().trim();
                    if (!confirm.equals(pass)) {
                        binding.confirmLayout.setError("Password mismatch");
                        return;
                    }
                    registerUser(email, pass);
                } else {
                    loginUser(email, pass);
                }
            }
        });
    }

    private void loginUser(String email, String pass) {
        Toast.makeText(this, "Logged in!", Toast.LENGTH_SHORT).show();
    }
    private void registerUser(String email, String pass) {
        Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
    }
}
