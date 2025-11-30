package com.example.myapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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


        EditText name = findViewById(R.id.txt_name);
        Button btn_clickkaro = findViewById(R.id.btn_clickkaro);
        EditText txt_password = findViewById(R.id.txt_password);
        EditText txt_phone = findViewById(R.id.txt_phone);
        RadioGroup radioGroupGender = findViewById(R.id.radioGroupGender);
        Spinner txt_city = findViewById(R.id.txt_city);
        CheckBox chk_english = findViewById(R.id.chk_english);
        CheckBox chk_hindi = findViewById(R.id.chk_hindi);
        CheckBox chk_marathi = findViewById(R.id.chk_marathi);



        name.postDelayed(() -> {
            name.requestFocus();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowInsetsController controller = name.getWindowInsetsController();
                if (controller != null) {
                    controller.show(WindowInsets.Type.ime());
                }
            } else {
                // Old method for older Android versions
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);


        btn_clickkaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder languages = new StringBuilder();
                int selected = radioGroupGender.getCheckedRadioButtonId();
                if(selected ==-1){
                    Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();

                 }
                RadioButton selectradiobutton = findViewById(selected);
                String gender = selectradiobutton.getText().toString();
                String nameText = name.getText().toString();
                String passwordText = txt_password.getText().toString();
                String phoneText = txt_phone.getText().toString();
                String selectedcity = txt_city.getSelectedItem().toString();
                 if(chk_english.isChecked()){
                     languages.append("English");

                 }
                 if(chk_hindi.isChecked()){
                     languages.append("Hindi");
                 }

                 if (chk_marathi.isChecked()){
                     languages.append("Marathi");
                 }
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,findViewById(R.id.custom_toast_container));
                ((TextView) layout.findViewById(R.id.toast_text)).setText("Name: " + nameText + "\nPassword: " + passwordText + "\nPhone: " + phoneText + "\nGender: " + gender + "\nCity: " + selectedcity + "\nLanguages: " + languages.toString());
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();


             //   Toast.makeText(getApplicationContext(), "Name: " + nameText + "\nPassword: " + passwordText + "\nPhone: " + phoneText + "\n", Toast.LENGTH_SHORT).show();



    }
});
    ;}}
