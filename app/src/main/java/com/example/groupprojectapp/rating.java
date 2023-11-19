package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class rating extends AppCompatActivity {

    Context context;
    FloatingActionButton fab;
    RatingBar ratingBar;
    EditText feedbackText;
    Button sendFeedbackButton;

    // Firebase Firestore reference
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ratingsRef = db.collection("ratings");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rating.this, MainActivity4.class);
                startActivity(intent);
                Toast.makeText(rating.this, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });

        ratingBar = findViewById(R.id.ratingBar);
        feedbackText = findViewById(R.id.editTextText3);
        sendFeedbackButton = findViewById(R.id.button3);

        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float ratingValue = ratingBar.getRating();
                String feedback = feedbackText.getText().toString().trim();

                if (ratingValue == 0) {
                    Toast.makeText(rating.this, "Please provide a rating.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (feedback.isEmpty()) {
                    Toast.makeText(rating.this, "Please provide some feedback.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the feedback to Firestore
                saveFeedbackToFirestore(ratingValue, feedback);
            }
        });
    }

    private void saveFeedbackToFirestore(float rating, String feedback) {
        // Create a Map with feedback data
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("rating", rating);
        feedbackData.put("feedback", feedback);

        // Add the data to Firestore
        ratingsRef.add(feedbackData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(rating.this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                    ratingBar.setRating(0);
                    feedbackText.setText("");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(rating.this, "Failed to send feedback. Please try again.", Toast.LENGTH_LONG).show();
                });
    }
}
