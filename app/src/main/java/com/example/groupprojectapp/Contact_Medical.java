package com.example.groupprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Contact_Medical extends AppCompatActivity {

    Context context;
    FloatingActionButton fab;

    Button submitButton;

    EditText editTextName, editTextPhone, editTextMessage;

    DatabaseReference mDatabase; // Firebase database reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_medical);

        fab = findViewById(R.id.fab); // Initialize the fab button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity4.class);
                startActivity(intent);
                Toast.makeText(Contact_Medical.this, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });

        submitButton = findViewById(R.id.submitButton);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String message = editTextMessage.getText().toString().trim();

                // Validate the form
                if (name.isEmpty() || phone.isEmpty() || message.isEmpty()) {
                    Toast.makeText(Contact_Medical.this, "Please fill out all fields.", Toast.LENGTH_LONG).show();
                } else {
                    // Create a HashMap with user's data
                    HashMap<String, Object> medicalData = new HashMap<>();
                    medicalData.put("name", name);
                    medicalData.put("phone", phone);
                    medicalData.put("message", message);

                    // Save the medical inquiry data to Firebase
                    saveMedicalInquiryToFirebase(medicalData);
                }
            }
        });
    }

    private void saveMedicalInquiryToFirebase(HashMap<String, Object> medicalData) {
        mDatabase.child("contact_medical").push().setValue(medicalData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Contact_Medical.this, "Medical inquiry submitted successfully.", Toast.LENGTH_LONG).show();
                            clearFormFields();
                        } else {
                            Toast.makeText(Contact_Medical.this, "Failed to submit medical inquiry.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void clearFormFields() {
        // Clear the input fields after successful form submission
        editTextName.setText("");
        editTextPhone.setText("");
        editTextMessage.setText("");
    }
}
