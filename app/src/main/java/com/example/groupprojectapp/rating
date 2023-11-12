package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class rating extends AppCompatActivity {


    Context context;
    FloatingActionButton fab;
    RatingBar ratingBar;
    EditText feedbackText;
    Button sendFeedbackButton;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);


        fab = findViewById(R.id.fab); // Initialize the fab button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rating.this, MainActivity4.class);
                context.startActivity(intent);
                Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });


        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize the UI components
        ratingBar = findViewById(R.id.ratingBar);
        feedbackText = findViewById(R.id.editTextText3);
        sendFeedbackButton = findViewById(R.id.button3);
        fab = findViewById(R.id.fab);


        // Setup the Send Feedback button click listener
        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float ratingValue = ratingBar.getRating();
                String feedback = feedbackText.getText().toString().trim();

                if (ratingValue == 0) {
                    Toast.makeText(rating.this, "Please provide a rating.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate feedback text
                if (feedback.isEmpty()) {
                    Toast.makeText(rating.this, "Please provide some feedback.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the feedback to Firebase Database
                saveFeedbackToFirebase(ratingValue, feedback);
            }
        });
    }

    private void saveFeedbackToFirebase(float rating, String feedback) {
        // Create a unique ID for each feedback
        String feedbackId = mDatabase.push().getKey();

        // Create a HashMap with feedback data
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("rating", rating);
        feedbackData.put("feedback", feedback);

        // Save the feedback to the "ratings" node in Firebase Database
        mDatabase.child("ratings").child(feedbackId).setValue(feedbackData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(rating.this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                            ratingBar.setRating(0);
                            feedbackText.setText("");
                        } else {
                            Toast.makeText(rating.this, "Failed to send feedback. Please try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}



