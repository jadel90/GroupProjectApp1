package com.example.groupprojectapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity4 extends AppCompatActivity {

    Context context;
    ImageView img;
    TextView tv1, tv2;

    FloatingActionButton fab2, fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        img = findViewById(R.id.img);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);



        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);

        Intent intent = getIntent();


        String name = intent.getExtras().getString("name");
        String info = intent.getExtras().getString("info");
        int image = intent.getExtras().getInt("image", 0);

        Toast.makeText(MainActivity4.this,image,Toast.LENGTH_LONG).show();


        tv1.setText(name);
        tv2.setText(info);
        img.setImageResource(image);



        //back button = MainActivity3
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                context.startActivity(intent);
            }
        });

        //home button = MainActivity
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(intent);



            }
        });
    }
}





