package com.example.myapplication;

public class Car {
    private String id;
    private String make;
    private String model;
    private int year;
    private double latitude;
    private double longitude;

    public Car() {
    }

    public Car(String id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}