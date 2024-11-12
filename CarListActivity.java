package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity implements CarAdapter.OnCarClickListener {
    private CarDataManager carDataManager;
    private List<Car> carList;
    private CarAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        carDataManager = new CarDataManager();
        carList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(carList, this);
        recyclerView.setAdapter(adapter);

        // Initialize and set up the FloatingActionButton
        FloatingActionButton addButton = findViewById(R.id.addCarButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(CarListActivity.this, AddCarActivity.class);
            startActivity(intent);
        });

        loadCars();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Add Test Cars");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            addTestCars();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // testing remove in sm time
    private void addTestCars() {
        // Add first test car
        Car car1 = new Car(null, "Toyota", "Camry", 2024);
        carDataManager.addCar(car1);

        // Add second test car
        Car car2 = new Car(null, "Honda", "Civic", 2023);
        carDataManager.addCar(car2);

        // Add more test cars if you want
        Car car3 = new Car(null, "Tesla", "Model 3", 2024);
        carDataManager.addCar(car3);

        Car car4 = new Car(null, "Ford", "Mustang", 2023);
        carDataManager.addCar(car4);

        Toast.makeText(this, "Test cars added", Toast.LENGTH_SHORT).show();
    }

    private void loadCars() {
        carDataManager.getAllCars(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                carList.clear();
                for (DataSnapshot carSnapshot : snapshot.getChildren()) {
                    Car car = carSnapshot.getValue(Car.class);
                    if (car != null) {
                        car.setId(carSnapshot.getKey()); // Important: set the ID from the snapshot
                        carList.add(car);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CarListActivity.this,
                        "Error loading cars: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCarClick(Car car) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("CAR_ID", car.getId());
        intent.putExtra("CURRENT_LAT", car.getLatitude());
        intent.putExtra("CURRENT_LNG", car.getLongitude());
        startActivity(intent);
    }
}