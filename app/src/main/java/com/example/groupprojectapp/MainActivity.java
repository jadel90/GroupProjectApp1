package com.example.groupprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the buttons inside the layout after setContentView
        Button sign_in = findViewById(R.id.btn_sign_up);
        Button login = findViewById(R.id.btn_login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Login button clicked");
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("MainActivity", "Failed to start MainActivity2", e);
                }
            }
        });


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent1);

                try {
                    startActivity(intent1);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("MainActivity", "Failed to start MainActivity3", e);
                }
            }
        });



    }
}
