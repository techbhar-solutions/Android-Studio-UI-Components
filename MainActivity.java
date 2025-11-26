package com.example.myapplication;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        EditText et_my_name;
        Button my_btn_submit;
        Button my_btn_clear;

        et_my_name = (EditText) findViewById(R.id.edittxt);
        my_btn_submit = (Button) findViewById(R.id.btn_submit);
        my_btn_clear = (Button) findViewById(R.id.btn_clear);

        my_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = et_my_name.getText().toString();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.custom_toast,findViewById(R.id.custom_toast_container));
               ((TextView)layout.findViewById(R.id.toast_text)).setText(message);
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_SHORT);
               toast.setView(layout);
               toast.show();



            }
        });

        my_btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            et_my_name.setText("");
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,findViewById(R.id.custom_toast_container));
            ((TextView)layout.findViewById(R.id.toast_text)).setText("Cleared");
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();



            }
        });
    }
}
