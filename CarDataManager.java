package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class CarDataManager {
    private DatabaseReference mDatabase;

    public CarDataManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addCar(Car car) {
        String carId = mDatabase.child("cars").push().getKey();
        car.setId(carId);
        mDatabase.child("cars").child(carId).setValue(car);
    }

    public void updateCarLocation(String carId, double latitude, double longitude) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("latitude", latitude);
        updates.put("longitude", longitude);
        mDatabase.child("cars").child(carId).updateChildren(updates);
    }

    public void getAllCars(ValueEventListener listener) {
        mDatabase.child("cars").addValueEventListener(listener);
    }
}