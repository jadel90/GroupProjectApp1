package com.example.groupprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Display_Patient_Details extends AppCompatActivity {

    Context context;
    TextView tvPatientName, tvPatientEmail, tvPatientDOB;
    TextView tvInsuranceCompany,  tvExpiryDate;
    RatingBar ratingBar;
    TextView tvReviews;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_patient_details);

        // Initialize UI components
        tvPatientName = findViewById(R.id.tvPatientName);
        tvPatientEmail = findViewById(R.id.tvPatientEmail);
        tvPatientDOB = findViewById(R.id.tvPatientDOB);
        tvInsuranceCompany = findViewById(R.id.tvInsuranceCompany);
        tvExpiryDate = findViewById(R.id.tvExpiryDate);
        ratingBar = findViewById(R.id.ratingBar);
        tvReviews = findViewById(R.id.tvReviews);

        fab = findViewById(R.id.fab); // Initialize the fab button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display_Patient_Details.this, MainActivity4.class);
                context.startActivity(intent);
                Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });

        // Replace 'your_patient_id' with the actual patient ID you want to display
        String patientId = "your_patient_id";

        // Initialize Firebase Database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("patients").child(patientId);

        // Listen for changes in the patient's data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve and display patient details
                    Patient patient = dataSnapshot.getValue(Patient.class);
                    if (patient != null) {
                        tvPatientName.setText("Name: " + patient.getName());
                        tvPatientEmail.setText("Email: " + patient.getEmail());
                        tvPatientDOB.setText("Date of Birth: " + patient.getDob());

                        // Retrieve and display insurance details
                        DataSnapshot insuranceSnapshot = dataSnapshot.child("insurance");
                        if (insuranceSnapshot.exists()) {
                            InsuranceDetails insurance = insuranceSnapshot.getValue(InsuranceDetails.class);
                            if (insurance != null) {
                                tvInsuranceCompany.setText("Insurance Company: " + insurance.getInsuranceCompany());
                                tvExpiryDate.setText("Expiry Date: " + insurance.getExpiryDate());
                            }
                        } else {
                            tvInsuranceCompany.setText("Insurance Company: Not available");
                            tvExpiryDate.setText("Expiry Date: Not available");
                        }

                        // Retrieve and display rating and reviews
                        DataSnapshot ratingsSnapshot = dataSnapshot.child("ratings");
                        if (ratingsSnapshot.exists()) {
                            float totalRating = 0;
                            int numReviews = 0;
                            for (DataSnapshot ratingSnapshot : ratingsSnapshot.getChildren()) {
                                RatingAndReview ratingAndReview = ratingSnapshot.getValue(RatingAndReview.class);
                                if (ratingAndReview != null) {
                                    totalRating += ratingAndReview.getRating();
                                    numReviews++;
                                }
                            }
                            if (numReviews > 0) {
                                float averageRating = totalRating / numReviews;
                                ratingBar.setRating(averageRating);
                                tvReviews.setText("Reviews: " + numReviews);
                            }
                        } else {
                            ratingBar.setRating(0);
                            tvReviews.setText("Reviews: 0");
                        }
                    }
                } else {
                    // Handle the case where the patient's data does not exist
                    Toast.makeText(context, "Patient data not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors here
                Toast.makeText(context, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
