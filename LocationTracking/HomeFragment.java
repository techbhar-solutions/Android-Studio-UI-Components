package com.example.retrofitexample;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.jspecify.annotations.Nullable;

public class HomeFragment extends Fragment {
    // 1. Google ka Location Manager (Waiter)




    private final ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permissions -> {
                Boolean fineLocationGranted = permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseLocationGranted = permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                if (fineLocationGranted != null && fineLocationGranted) {

                    // Precise location access granted
                    Toast.makeText(getContext(), "Precise Location Granted", Toast.LENGTH_SHORT).show();
                } else if (coarseLocationGranted != null && coarseLocationGranted) {
                    // Only approximate location access granted
                    Toast.makeText(getContext(), "Approximate Location Granted", Toast.LENGTH_SHORT).show();
                } else {
                    // No location access granted
                    Toast.makeText(getContext(), "Location Permission Denied", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homeview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button mybtn = view.findViewById(R.id.button);
        TextView tvLocation = view.findViewById(R.id.tvLocation);
       // Button callbtn = view.findViewById(R.id.btncall);

        mybtn.setOnClickListener(v -> {
            locationPermissionRequest.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        });

        if(getArguments() != null){
            Double latitude = getArguments().getDouble("latitude");
            Double longitude = getArguments().getDouble("longitude");
            tvLocation.setText("latitude: "+latitude+"longitude"+longitude);

        }

    }
}
