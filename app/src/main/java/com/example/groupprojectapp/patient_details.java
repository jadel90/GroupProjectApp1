package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class patient_details extends AppCompatActivity {

    Context context;
    FloatingActionButton fab;

    DatabaseReference databaseReference;
    EditText editTextEmail, editTextPhoneNumber, editTextAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);


        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity4.class);
                context.startActivity(intent);
            }
        });

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("patients");

        // Initialize UI components
        editTextEmail = findViewById(R.id.editTextText8);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAddress = findViewById(R.id.editTextTextPostalAddress);

        // Add a click listener for the "Edit" button
        Button buttonEdit = findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enable editing of EditText fields
                editTextEmail.setEnabled(true);
                editTextPhoneNumber.setEnabled(true);
                editTextAddress.setEnabled(true);
            }
        });

        // Add a click listener for the "Save" button
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from EditText fields
                String email = editTextEmail.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String address = editTextAddress.getText().toString();

                // Create an InsuranceDetails object (you should obtain these values from user input)
                String insuranceCompany = "YourInsuranceCompany"; // Replace with actual value
                String policyNumber = "YourPolicyNumber"; // Replace with actual value
                String expiryDate = "YourExpiryDate"; // Replace with actual value

                // Create a Patient object with InsuranceDetails
                Patient patient = new Patient(email, phoneNumber, address, new InsuranceDetails(insuranceCompany, policyNumber, expiryDate));

                // Push the patient data to Firebase Realtime Database
                databaseReference.push().setValue(patient);

                // Disable editing of EditText fields
                editTextEmail.setEnabled(false);
                editTextPhoneNumber.setEnabled(false);
                editTextAddress.setEnabled(false);

                // Clear EditText fields
                editTextEmail.setText("");
                editTextPhoneNumber.setText("");
                editTextAddress.setText("");
            }
        });

        // Add a click listener for the "Update" button
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from EditText fields
                String email = editTextEmail.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String address = editTextAddress.getText().toString();

                // Create a Patient object with InsuranceDetails (you should obtain these values from user input)
                String insuranceCompany = "YourUpdatedInsuranceCompany"; // Replace with actual value
                String policyNumber = "YourUpdatedPolicyNumber"; // Replace with actual value
                String expiryDate = "YourUpdatedExpiryDate"; // Replace with actual value

                // Create a Patient object with updated InsuranceDetails
                Patient patient = new Patient(email, phoneNumber, address, new InsuranceDetails(insuranceCompany, policyNumber, expiryDate));

                // Push the updated patient data to Firebase Realtime Database
                databaseReference.push().setValue(patient);

                // Disable editing of EditText fields
                editTextEmail.setEnabled(false);
                editTextPhoneNumber.setEnabled(false);
                editTextAddress.setEnabled(false);

                // Clear EditText fields
                editTextEmail.setText("");
                editTextPhoneNumber.setText("");
                editTextAddress.setText("");
            }
        });
    }
}






