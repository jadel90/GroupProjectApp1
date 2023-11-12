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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class insurance extends AppCompatActivity {

    Context context;
    FloatingActionButton fab;
    EditText insuranceCompanyEditText, policyNumberEditText, expiryDateEditText;
    Button submitButton;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("insurance details");

        insuranceCompanyEditText = findViewById(R.id.editTextText6);
        policyNumberEditText = findViewById(R.id.editTextText8);
        expiryDateEditText = findViewById(R.id.editTextDate3);
        submitButton = findViewById(R.id.button4);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInsuranceDetails();
            }
        });

        // Initialize the fab button and set its click listener
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity4 when FAB is clicked
                Intent intent = new Intent(insurance.this, MainActivity4.class);
                startActivity(intent);
                Toast.makeText(insurance.this, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveInsuranceDetails() {
        String insuranceCompany = insuranceCompanyEditText.getText().toString().trim();
        String policyNumber = policyNumberEditText.getText().toString().trim();
        String expiryDate = expiryDateEditText.getText().toString().trim();

        // Check if any field is empty
        if (insuranceCompany.isEmpty() || policyNumber.isEmpty() || expiryDate.isEmpty()) {
            // Handle empty fields
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique key for each insurance entry
        String insuranceId = databaseReference.push().getKey();

        // Create an InsuranceDetails object to store the data
        InsuranceDetails insuranceDetails = new InsuranceDetails(insuranceCompany, policyNumber, expiryDate);

        // Save the data to Firebase under "insurance details" collection
        databaseReference.child(insuranceId).setValue(insuranceDetails);

        // Clear the input fields
        insuranceCompanyEditText.setText("");
        policyNumberEditText.setText("");
        expiryDateEditText.setText("");

        // Show a success message
        Toast.makeText(this, "Insurance details saved successfully", Toast.LENGTH_SHORT).show();
    }
}
