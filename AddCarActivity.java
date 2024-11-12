package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCarActivity extends AppCompatActivity {
    private EditText makeInput;
    private EditText modelInput;
    private EditText yearInput;
    private EditText latitudeInput;
    private EditText longitudeInput;
    private CarDataManager carDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Initialize CarDataManager
        carDataManager = new CarDataManager();

        // Initialize views
        makeInput = findViewById(R.id.makeInput);
        modelInput = findViewById(R.id.modelInput);
        yearInput = findViewById(R.id.yearInput);
        latitudeInput = findViewById(R.id.latitudeInput);
        longitudeInput = findViewById(R.id.longitudeInput);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveCar());
    }

    private void saveCar() {
        String make = makeInput.getText().toString().trim();
        String model = modelInput.getText().toString().trim();
        String yearStr = yearInput.getText().toString().trim();
        String latStr = latitudeInput.getText().toString().trim();
        String lonStr = longitudeInput.getText().toString().trim();

        // Validate inputs
        if (make.isEmpty() || model.isEmpty() || yearStr.isEmpty() ||
                latStr.isEmpty() || lonStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int year = Integer.parseInt(yearStr);
            double latitude = Double.parseDouble(latStr);
            double longitude = Double.parseDouble(lonStr);

            // Create and save the car
            Car car = new Car(null, make, model, year);
            car.setLatitude(latitude);
            car.setLongitude(longitude);

            carDataManager.addCar(car);

            Toast.makeText(this, "Car added successfully", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity and return to list

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}