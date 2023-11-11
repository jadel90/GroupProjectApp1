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

import java.util.HashMap;


public class MainActivity3 extends AppCompatActivity {


    TextInputEditText editTextEmail, editTextPassword,  editTextName, editTextPhoneNumber, editTextDOB;;
    Button buttonSignUp;

    ProgressBar progressBar;

    TextView textViewLogin;




    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        mAuth = FirebaseAuth.getInstance();

        //new code:  save data to firebase
        // Firebase Database reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextName = findViewById(R.id.name);
        editTextPhoneNumber = findViewById(R.id.phone_number);
        editTextDOB = findViewById(R.id.dob);

        buttonSignUp = findViewById(R.id.btn_register);

        progressBar = findViewById(R.id.progressBar);
        textViewLogin = findViewById(R.id.loginNow);


        // login
        // Navigate to login screen
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        // sign up button
        // Handle sign up
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // new code
                // Get the user input
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // new code
                String name = editTextName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String dob = editTextDOB.getText().toString().trim();



                // Check for valid inputs
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity3.this, "Enter email", Toast.LENGTH_LONG).show();
                    editTextEmail.setError("Enter email");
                    return;
                }


                if (TextUtils.isEmpty(password) || password.length() < 6) {
                  Toast.makeText(MainActivity3.this, "Enter password", Toast.LENGTH_LONG).show();
                  editTextPassword.setError("Password should be at least 6 characters");
                  return;
                }

                progressBar.setVisibility(View.VISIBLE);


                // Create user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // If authentication is successful, save additional user data to Firebase database.

                                    // new code
                                    saveUserData(email, name, phoneNumber, dob);
                                } else {
                                    Toast.makeText(MainActivity3.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            };
                        });
            }
                private void saveUserData(String email, String name, String phoneNumber, String dob) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("Email", email);
                    userDataMap.put("Name", name);
                    userDataMap.put("Phone Number", phoneNumber);
                    userDataMap.put("DOB", dob);

                    String currentUserId = mAuth.getCurrentUser().getUid();
                    databaseReference.child(currentUserId).setValue(userDataMap)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(getApplicationContext(), "Account created and data added successfully", Toast.LENGTH_LONG).show();
                                // Navigate to the next activity after sign up
                                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                                startActivity(intent);
                                finish();
                            })
                            .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Data save failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
                }


        });
    }
}

