package com.example.retrofitexample;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    EditText txtregisterid, txtregisterpass, txtloginid, txtloginpass;
    Button btnregister, btnlogin;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profileview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Views
        txtregisterid = view.findViewById(R.id.txtregisterid);
        txtregisterpass = view.findViewById(R.id.txtregisterpass);
        txtloginid = view.findViewById(R.id.txtloginid);
        txtloginpass = view.findViewById(R.id.txtloginpass);
        btnregister = view.findViewById(R.id.btnregister);
        btnlogin = view.findViewById(R.id.btnlogin);

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("mypref", MODE_PRIVATE);

        btnregister.setOnClickListener(v -> {
            try {
                String id = txtregisterid.getText().toString();
                String pass = txtregisterpass.getText().toString();

                if (!id.isEmpty() && !pass.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userid", id);
                    editor.putString("password", pass);
                    editor.apply();
                    txtregisterid.setText("");
                    txtregisterpass.setText("");
                    Toast.makeText(requireContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnlogin.setOnClickListener(v -> {
            try {
                String storedId = sharedPreferences.getString("userid", "");
                String storedPass = sharedPreferences.getString("password", "");

                String loginId = txtloginid.getText().toString();
                String loginPass = txtloginpass.getText().toString();

                if (loginId.equals(storedId) && loginPass.equals(storedPass) && !loginId.isEmpty()) {
                    txtregisterid.setText("");
                    txtregisterpass.setText("");
                    Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
