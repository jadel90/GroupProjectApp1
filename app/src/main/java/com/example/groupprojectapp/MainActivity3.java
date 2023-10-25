package com.example.groupprojectapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MainActivity3 extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Health> arrayList = new ArrayList<>();

    FloatingActionButton fab;

    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.tv_email);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fab);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
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
        arrayList.add(new Health(R.drawable.support, health_name[8]));
        arrayList.add(new Health(R.drawable.gp_details, health_name[9]));
        arrayList.add(new Health(R.drawable.health_summary, health_name[10]));



        //Display Recycler View
        HealthRecycler dogRecycler = new HealthRecycler(this, arrayList, fab);
        recyclerView.setAdapter(dogRecycler);

        FloatingActionButton fabCall = findViewById(R.id.fab2);

        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "35383456789"));
                startActivity(intent);
            }
        });




//        @Override
//        public void onItemClick(Health health) {
//            Intent intent;
//            switch (health.getName()) { // Assuming getName() retrieves the title of the health object
//                case "appointment":
//                    intent = new Intent(this, AppointmentActivity.class);
//                    startActivity(intent);
//                    break;
//                case "ai":
//                    intent = new Intent(this, AiActivity.class);
//                    startActivity(intent);
//                    break;

    }
}



