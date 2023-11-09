package com.example.groupprojectapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity3 extends AppCompatActivity {


    TextInputEditText editTextEmail, editTextPassword;
    Button buttonSignUp;

//    ActivityMainBinding binding;
    String email, password, name;
    int phone_number, dob;
    FirebaseDatabase db;
    DatabaseReference reference;

    FirebaseAuth mAuth;


    ProgressBar progressBar;


    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonSignUp = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);





        // login
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
//                finish();
            }
        });


        // sign up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity3.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;


                }


                if (TextUtils.isEmpty(password) || password.length() < 6) {


//                    editTextPassword.setError("Password should be at least 6 characters long.");
                    Toast.makeText(MainActivity3.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }




                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity3.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();






                                }


                                else {


                                    Toast.makeText(MainActivity3.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();


                                }
                            }
                        });


            }
        });








    }
}


