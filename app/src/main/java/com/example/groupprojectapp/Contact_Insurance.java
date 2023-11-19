package com.example.groupprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Contact_Insurance extends AppCompatActivity {

    // Firebase Firestore reference
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference insuranceInquiriesRef = db.collection("insurance_inquiries");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_insurance);

        // Initialize the FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);

        // Set an OnClickListener for the FloatingActionButton to navigate to the MainActivity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contact_Insurance.this, MainActivity.class); // Replace MainActivity with your home or back activity
                startActivity(intent);
                Toast.makeText(Contact_Insurance.this, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });

        // Initialize form fields
        EditText nameEditText = findViewById(R.id.editTextText11);
        EditText companyEditText = findViewById(R.id.editTextText14);
        EditText phoneEditText = findViewById(R.id.editTextPhone);
        EditText messageEditText = findViewById(R.id.editTextTextMultiLine);
        Button submitButton = findViewById(R.id.button6);

        // Set an OnClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String company = companyEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String message = messageEditText.getText().toString().trim();

                // Validate the form fields
                if (name.isEmpty() || company.isEmpty() || phone.isEmpty() || message.isEmpty()) {
                    Toast.makeText(Contact_Insurance.this, "Please fill out all fields.", Toast.LENGTH_LONG).show();
                } else {
                    // Create a Map with user's data
                    Map<String, Object> insuranceData = new HashMap<>();
                    insuranceData.put("name", name);
                    insuranceData.put("company", company);
                    insuranceData.put("phone", phone);
                    insuranceData.put("message", message);

                    // Save the inquiry data to Firestore
                    saveInquiryToFirestore(insuranceData);
                }
            }
        });
    }

    private void saveInquiryToFirestore(Map<String, Object> insuranceData) {
        // Add the data to Firestore
        insuranceInquiriesRef.add(insuranceData)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Contact_Insurance.this, "Inquiry submitted successfully.", Toast.LENGTH_LONG).show();
                            // Optionally clear the fields after submission
                            clearFormFields();
                        } else {
                            Toast.makeText(Contact_Insurance.this, "Failed to submit inquiry.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void clearFormFields() {
        // Clear the input fields after successful form submission
        ((EditText) findViewById(R.id.editTextText11)).setText("");
        ((EditText) findViewById(R.id.editTextText14)).setText("");
        ((EditText) findViewById(R.id.editTextPhone)).setText("");
        ((EditText) findViewById(R.id.editTextTextMultiLine)).setText("");
    }
}





