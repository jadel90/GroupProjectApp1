package com.example.groupprojectapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class gp_details extends AppCompatActivity {


    RecyclerView recyclerView1;
    ArrayList<GP> arrayList = new ArrayList<>();

    Context context;

    FloatingActionButton fab;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp_details);


        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fab);


        // Health Recycler

        String [] doctor_names = getResources().getStringArray(R.array.doctor_names);
        String [] doctor_info = getResources().getStringArray(R.array.doctor_info);

        //Health page
        //11 screens.
        arrayList.add(new GP(R.drawable.d1, doctor_names[0],doctor_info[0]));
        arrayList.add(new GP(R.drawable.d2, doctor_names[1],doctor_info[1]));
        arrayList.add(new GP(R.drawable.d3, doctor_names[2],doctor_info[2]));
        arrayList.add(new GP(R.drawable.d4, doctor_names[3],doctor_info[4]));


        //Display Recycler View
        gpRecycler gRecycler = new gpRecycler(this, arrayList, fab);
        recyclerView1.setAdapter(gRecycler);



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