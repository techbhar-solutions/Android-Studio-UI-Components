package com.example.retrofitexample;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

public class HomeFragment extends Fragment {

    // --- Location Tracking Fields ---
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private boolean isTracking = false;

    // --- UI Elements ---
    private TextView tvLocation;
    private Button callbtn;

    private static final int FOREGROUND_PERMISSION_CODE = 100;
    private static final int BACKGROUND_PERMISSION_CODE = 101;

    private final ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher =
            registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    startLocationTracking();
                } else {
                    Toast.makeText(requireContext(), "Location must be enabled to start tracking.", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        return inflater.inflate(R.layout.homeview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvLocation = view.findViewById(R.id.tvLocation);
        callbtn = view.findViewById(R.id.btnlocation);

        callbtn.setOnClickListener(v -> {
            if (isTracking) {
                stopLocationTracking();
            } else {
                checkpermission();
            }
        });

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        tvLocation.setText("Lat: " + location.getLatitude() + ", Lon: " + location.getLongitude());
                    }
                }
            }
        };
    }

    private void checkpermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            checkbackgroundermission();
        } else {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FOREGROUND_PERMISSION_CODE);
        }
    }

    private void checkbackgroundermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                checkLocationSettings();
            } else {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Background Location Required")
                        .setMessage("Driver mode ke liye 'Allow all the time' zaroori hai. Settings mein jakar select karein.")
                        .setPositiveButton("Go to Settings", (dialog, which) -> requestPermissions(
                                new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                                BACKGROUND_PERMISSION_CODE))
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        } else {
            checkLocationSettings();
        }
    }

    @SuppressWarnings("deprecation")
    private void checkLocationSettings() {
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).build();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(requireActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(requireActivity(), locationSettingsResponse -> startLocationTracking());

        task.addOnFailureListener(requireActivity(), e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(resolvable.getResolution()).build();
                    locationSettingsLauncher.launch(intentSenderRequest);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FOREGROUND_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkpermission();
            } else {
                Toast.makeText(requireContext(), "Foreground Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == BACKGROUND_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationSettings();
            } else {
                Toast.makeText(requireContext(), "Background permission is required.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressWarnings("MissingPermission") // Permissions are checked in the flow before calling this.
    private void startLocationTracking() {
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build();

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

        isTracking = true;
        callbtn.setText("Stop Tracking");
        tvLocation.setText("Status: Tracking ON...");
        tvLocation.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_700));
        Toast.makeText(requireContext(), "Location Tracking Started!", Toast.LENGTH_SHORT).show();
    }

    private void stopLocationTracking() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
        isTracking = false;
        callbtn.setText("Start Tracking");
        tvLocation.setText("Status: Tracking OFF");
        tvLocation.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
        Toast.makeText(requireContext(), "Location Tracking Stopped!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isTracking) {
            stopLocationTracking();
        }
    }
}
