package com.example.groupprojectapp;

import android.content.Context;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp_details);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fab);

        // Health Recycler
        // Get the doctor names and info from resources
        String[] doctor_names = getResources().getStringArray(R.array.doctor_names);
        String[] doctor_info = getResources().getStringArray(R.array.doctor_info);

        
//         Populate the GP array
  
        for (int i = 0; i < doctor_names.length; i++) {
            int imageResourceId = getResources().getIdentifier("d" + (i + 1), "drawable", getPackageName());
            arrayList.add(new GP(imageResourceId, doctor_names[i], doctor_info[i]));
        }

        // Display Recycler View
        // Set up the RecyclerView with the gpRecycler adapter
        gpRecycler gRecycler = new gpRecycler(this, arrayList, fab);
        recyclerView1.setAdapter(gRecycler);
    }
}




