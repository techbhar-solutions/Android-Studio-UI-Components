package com.example.mynewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynewapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ViewBinding init
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Insets / Notch / System bar handling
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
            return insets;
        });

        dbHelper = new DBHelper(this);

        binding.btnRegister.setOnClickListener(v -> registerUser());

        binding.btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {

        // 1️⃣ UI thread se data lo
        String name = binding.etName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rb = findViewById(selectedId);
        String gender = rb.getText().toString();

        // 2️⃣ Background thread me DB ka kaam
        new Thread(() -> {
            dbHelper.registerUserHelper(name, email, password, gender);

            // 3️⃣ UI thread pe result
            runOnUiThread(() -> {
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();

                binding.etName.setText("");
                binding.etEmail.setText("");
                binding.etPassword.setText("");
                binding.radioGroup.clearCheck();
            });
        }).start();
    }

}
