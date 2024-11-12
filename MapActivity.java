package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    private String carId;
    private CarDataManager carDataManager;
    private double currentLat;
    private double currentLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        carDataManager = new CarDataManager();
        carId = getIntent().getStringExtra("CAR_ID");
        currentLat = getIntent().getDoubleExtra("CURRENT_LAT", 0.0);
        currentLng = getIntent().getDoubleExtra("CURRENT_LNG", 0.0);

        Button pickLocationButton = findViewById(R.id.pickLocationButton);
        pickLocationButton.setOnClickListener(v -> openMapsForLocation());
    }

    private void openMapsForLocation() {
        // Create a geo location URI
        String geoUri = "geo:0,0?q=" + currentLat + "," + currentLng + "(Car Location)";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        intent.setPackage("com.google.android.apps.maps"); // Specify Google Maps app

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If Google Maps is not installed, open in any available map app
            intent.setPackage(null);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                double latitude = extras.getDouble("latitude");
                double longitude = extras.getDouble("longitude");
                updateCarLocation(latitude, longitude);
            }
        }
    }

    private void updateCarLocation(double latitude, double longitude) {
        carDataManager.updateCarLocation(carId, latitude, longitude);
        Toast.makeText(this, "Location updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}