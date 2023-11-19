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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextName, editTextPhoneNumber, editTextDOB;
    Button buttonSignUp;
    ProgressBar progressBar;
    TextView textViewLogin;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextName = findViewById(R.id.name);
        editTextPhoneNumber = findViewById(R.id.phone_number);
        editTextDOB = findViewById(R.id.dob);

        buttonSignUp = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textViewLogin = findViewById(R.id.loginNow);

        // Navigate to login screen
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Handle sign up button click
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String dob = editTextDOB.getText().toString().trim();

                // Check for valid inputs
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity3.this, "Please enter both email and password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(MainActivity3.this, "Password should be at least 6 characters", Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Create user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // If authentication is successful, save additional user data to Cloud Firestore.
                                    saveUserData(email, name, phoneNumber, dob);
                                } else {
                                    Toast.makeText(MainActivity3.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }

            private void saveUserData(String email, String name, String phoneNumber, String dob) {
                String currentUserId = mAuth.getCurrentUser().getUid();

                // Create a map with user data
                Map<String, Object> user = new HashMap<>();
                user.put("Email", email);
                user.put("Name", name);
                user.put("Phone Number", phoneNumber);
                user.put("DOB", dob);

                // Add user data to Cloud Firestore
                db.collection("users")
                        .document(currentUserId)
                        .set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Account created and data added successfully", Toast.LENGTH_LONG).show();
                                    // Navigate to the next activity after sign up
                                    Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Data save failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
