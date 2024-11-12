package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> carList;
    private OnCarClickListener listener;

    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    public CarAdapter(List<Car> carList, OnCarClickListener listener) {
        this.carList = carList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.bind(car, listener);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        private TextView makeModelText;
        private TextView yearText;
        private TextView locationText;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            makeModelText = itemView.findViewById(R.id.makeModelText);
            yearText = itemView.findViewById(R.id.yearText);
            locationText = itemView.findViewById(R.id.locationText);
        }

        public void bind(final Car car, final OnCarClickListener listener) {
            makeModelText.setText(car.getMake() + " " + car.getModel());
            yearText.setText(String.valueOf(car.getYear()));
            locationText.setText("Location: " + car.getLatitude() + ", " + car.getLongitude());

            itemView.setOnClickListener(v -> listener.onCarClick(car));
        }
    }
}