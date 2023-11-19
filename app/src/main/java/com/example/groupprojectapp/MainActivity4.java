package com.example.groupprojectapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {




    RecyclerView recyclerView;
    ArrayList<Health> arrayList = new ArrayList<>();





    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

    Button logoutBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        textView = findViewById(R.id.tv_email);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        logoutBtn = findViewById(R.id.logoutButton);



        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }


        else {
            textView.setText(user.getEmail());
        }


        // In MainActivity4.java or where you want to handle the logout
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out from Firebase
                auth.signOut();

                // Display a Toast message
                Toast.makeText(MainActivity4.this, "You have logged out.", Toast.LENGTH_SHORT).show();

                // After logout, start the LoginActivity and clear the activity stack
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });



        // Health Recycler


        String [] health_name = getResources().getStringArray(R.array.screen_names);


        //Health page
        //11 screens.
        arrayList.add(new Health(R.drawable.appointment, health_name[0]));
        arrayList.add(new Health(R.drawable.insurance, health_name[1]));
        arrayList.add(new Health(R.drawable.ai, health_name[2]));
        arrayList.add(new Health(R.drawable.claim, health_name[3]));
        arrayList.add(new Health(R.drawable.messages, health_name[4]));
        arrayList.add(new Health(R.drawable.profile, health_name[5]));
        arrayList.add(new Health(R.drawable.rating, health_name[6]));
        arrayList.add(new Health(R.drawable.request_professional, health_name[7]));
        arrayList.add(new Health(R.drawable.insuranceform, health_name[8]));
        arrayList.add(new Health(R.drawable.patientform, health_name[9]));
        arrayList.add(new Health(R.drawable.gp_details, health_name[10]));
        arrayList.add(new Health(R.drawable.health_summary, health_name[11]));

        //Display Recycler View
        HealthRecycler healthRecycler = new HealthRecycler(this, arrayList, logoutBtn);
        recyclerView.setAdapter(healthRecycler);




    }
}







