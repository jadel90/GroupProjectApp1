package com.example.groupprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DoctorDetailsActivity extends AppCompatActivity {


    Context context;
    ImageView imageView;
    TextView nameTextView, infoTextView;

    FloatingActionButton fab, fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);


        imageView = findViewById(R.id.doctor_image);
        nameTextView = findViewById(R.id.doctor_name);
        infoTextView = findViewById(R.id.doctor_info);

        fab = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);

        // Get the extras from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int image = extras.getInt("image");
            String name = extras.getString("name");
            String info = extras.getString("info");

            // Set the views with the data from the intent
            imageView.setImageResource(image);
            nameTextView.setText(name);
            infoTextView.setText(info);
        }

        //back button = MainActivity2
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, gp_details.class);
                context.startActivity(intent);
            }
        });






        //home button = MainActivity
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, MainActivity4.class);
                startActivity(intent);




            }
        });


    }
}
