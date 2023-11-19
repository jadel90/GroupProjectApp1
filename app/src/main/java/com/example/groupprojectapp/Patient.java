package com.example.groupprojectapp;

public class Patient {
    private String name;
    private String email;
    private String dob;



    // Default constructor (required for Firebase)
    public Patient() {
    }

    public Patient(String name, String email, String dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }


}
