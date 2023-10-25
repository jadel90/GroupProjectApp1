package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class rating extends AppCompatActivity {


    Context context;
    FloatingActionButton fab;
//    private RatingBar ratingBar;
//    private TextView textViewRating, textViewFeedbackPrompt;
//    private EditText editTextFeedback;
//    private Button buttonSendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        fab = findViewById(R.id.fab); // Initialize the fab button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity3.class);
                context.startActivity(intent);
                Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
}


        // Initialize Views
//        ratingBar = findViewById(R.id.ratingBar);
//        textViewRating = findViewById(R.id.textView10);
//        textViewFeedbackPrompt = findViewById(R.id.textView11);
//        editTextFeedback = findViewById(R.id.editTextText3);
//        buttonSendFeedback = findViewById(R.id.button3);
//
//        // Set listeners
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                textViewRating.setText(String.format("Rating: %.1f", rating));
//            }
//        });
//
//        buttonSendFeedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendFeedback();
//            }
//        });
//    }
//
//    private void sendFeedback() {
//        float rating = ratingBar.getRating();
//        String feedback = editTextFeedback.getText().toString().trim();
//
//        // Check if feedback is not empty
//        if(feedback.isEmpty()) {
//            Toast.makeText(this, "Please enter feedback", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Perform your feedback sending logic here (e.g., API call)
//
//        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
        // Optionally, you might want to navigate the user to another screen or reset the feedback form.
//    }
//}
