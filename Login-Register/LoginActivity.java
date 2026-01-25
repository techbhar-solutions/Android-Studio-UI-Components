package com.example.mynewapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynewapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private boolean isPasswordVisible = false;

    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    // Session ke liye Constants
    private static final String PREF_NAME = "MyappPref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_EMAIL = "userEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ViewBinding init
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
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

        // 2. Database aur SharedPreferences initialize karein
        dbHelper = new DBHelper(this);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        // --- AUTO LOGIN CHECK ---
        // Agar user pehle se login hai, to Login screen mat dikhao, seedha Main Activity par bhejo
        if (sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)) {
            navigateToHome();
        }

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // yahan login ka code likho

                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();


                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{

                    // Database Check
                    boolean checkUser = dbHelper.checkLogin(email, password);
                    if (checkUser) {
                        // Login Successful!
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        // Session Save karo (Taaki agli baar login na maange)
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(KEY_IS_LOGGED_IN, true);
                        editor.putString(KEY_EMAIL, email);
                        editor.apply();

                        // Home Page par jao
                        navigateToHome();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        binding.etPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.etPassword.getRight() - binding.etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (isPasswordVisible) {
                        // Hide password
                        binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        binding.etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_closed, 0);
                        isPasswordVisible = false;
                    } else {
                        // Show password
                        binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        binding.etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_open, 0);
                        isPasswordVisible = true;
                    }
                    binding.etPassword.setSelection(binding.etPassword.getText().length());
                    return true;
                }
            }
            return false;
        });
    }

    // Home Page par jaane ka function
    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class); // Apni Home Activity ka naam yahan likhein
        startActivity(intent);
        finish(); // Login screen ko kill kar do taaki Back dabane par wapas login par na aaye
    }
}
