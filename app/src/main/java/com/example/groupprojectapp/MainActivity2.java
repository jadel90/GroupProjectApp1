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

public class MainActivity2 extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonSignUp;

    FirebaseAuth mAuth;

    ProgressBar progressBar;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // sign up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity2.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;

                }

                if (TextUtils.isEmpty(password) || password.length() < 6) {

                    editTextPassword.setError("Password should be at least 6 characters long.");
                    Toast.makeText(MainActivity2.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity2.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();



                                }

                                else {

                                    Toast.makeText(MainActivity2.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });




        }
}


//package com.example.groupprojectapp;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//
//public class MainActivity2 extends AppCompatActivity {
//
//
//    EditText editTextText2;
//    EditText editTextTextPassword3;
//    Button signup_button;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//
//        editTextText2 = findViewById(R.id.editTextText2);
//        editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
//        signup_button = findViewById(R.id.signup_button);
//
//
//        signup_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Retrieve the entered username and password
//                String username = editTextText2.getText().toString();
//                String password = editTextTextPassword3.getText().toString();
//
//
//                // Perform the signup logic here (e.g., send data to a server, create an account, etc.)
//
//
//                // After successful signup, you can navigate to the dashboard or another appropriate screen
//
//
//                String message = String.valueOf(editTextText2.getText());
//
//
//                Intent new_intent = new Intent(MainActivity2.this, MainActivity3.class);
//                new_intent.putExtra("name", message);
//                startActivity(new_intent);
//
//
//            }
//        });
//    }
//}
