package com.example.groupprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Questionnaire_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        // Find the buttons by their IDs
        Button buttonAsthma = findViewById(R.id.buttonAsthma);
        Button buttonHeart = findViewById(R.id.buttonHeart);
        Button buttonLungCancer = findViewById(R.id.buttonLungCancer);

        // Set click listeners for the buttons
        buttonAsthma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for the "Asthma" button
                // You can navigate to the Asthma questionnaire activity or perform any other action here
                Intent intent = new Intent(Questionnaire_Activity.this, Asthma_Questionnaire.class);
                startActivity(intent);
            }
        });

        buttonHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for the "Heart" button
                // You can navigate to the Heart questionnaire activity or perform any other action here
            }
        });

        buttonLungCancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for the "Lung cancer" button
                // You can navigate to the Lung cancer questionnaire activity or perform any other action here
            }
        });


    }
}
