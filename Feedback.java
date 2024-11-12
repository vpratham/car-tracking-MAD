package com.example.myapplication;

public class Feedback {
    private String id;
    private String name;
    private String email;
    private String contact;
    private String message;

    // Required empty constructor for Firebase
    public Feedback() {
    }

    public Feedback(String id, String name, String email, String contact, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.message = message;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}