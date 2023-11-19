package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class patient_details extends AppCompatActivity {

    Context context;
    FloatingActionButton fab;
    EditText editTextEmail, editTextPhoneNumber, editTextAddress;
    Button buttonEdit, buttonSave, buttonUpdate;

    // Firebase Firestore reference
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference patientsRef = db.collection("patients");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        editTextEmail = findViewById(R.id.editTextText8);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAddress = findViewById(R.id.editTextTextPostalAddress);

        buttonEdit = findViewById(R.id.buttonEdit);
        buttonSave = findViewById(R.id.buttonSave);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enable editing of EditText fields
                editTextEmail.setEnabled(true);
                editTextPhoneNumber.setEnabled(true);
                editTextAddress.setEnabled(true);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatientDetails();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePatientDetails();
            }
        });

        // Initialize the fab button and set its click listener
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity4 when FAB is clicked
                Intent intent = new Intent(patient_details.this, MainActivity4.class);
                startActivity(intent);
                Toast.makeText(patient_details.this, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void savePatientDetails() {
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        // Check if any field is empty
        if (email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            // Handle empty fields
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Map to store the patient details
        Map<String, Object> patientData = new HashMap<>();
        patientData.put("email", email);
        patientData.put("phoneNumber", phoneNumber);
        patientData.put("address", address);

        // Add the data to Firestore
        patientsRef.add(patientData)
                .addOnSuccessListener(documentReference -> {
                    // Disable editing of EditText fields
                    editTextEmail.setEnabled(false);
                    editTextPhoneNumber.setEnabled(false);
                    editTextAddress.setEnabled(false);

                    // Clear EditText fields
                    editTextEmail.setText("");
                    editTextPhoneNumber.setText("");
                    editTextAddress.setText("");

                    // Show a success message
                    Toast.makeText(this, "Patient details saved successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(this, "Failed to save patient details", Toast.LENGTH_SHORT).show();
                });
    }

    private void updatePatientDetails() {
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        // Check if any field is empty
        if (email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            // Handle empty fields
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Map to store the updated patient details
        Map<String, Object> updatedPatientData = new HashMap<>();
        updatedPatientData.put("email", email);
        updatedPatientData.put("phoneNumber", phoneNumber);
        updatedPatientData.put("address", address);

        // Add the updated data to Firestore
        patientsRef.add(updatedPatientData)
                .addOnSuccessListener(documentReference -> {
                    // Disable editing of EditText fields
                    editTextEmail.setEnabled(false);
                    editTextPhoneNumber.setEnabled(false);
                    editTextAddress.setEnabled(false);

                    // Clear EditText fields
                    editTextEmail.setText("");
                    editTextPhoneNumber.setText("");
                    editTextAddress.setText("");

                    // Show a success message
                    Toast.makeText(this, "Patient details updated successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(this, "Failed to update patient details", Toast.LENGTH_SHORT).show();
                });
    }
}
