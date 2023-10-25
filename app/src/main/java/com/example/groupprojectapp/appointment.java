package com.example.groupprojectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class appointment extends AppCompatActivity {


//    private EditText mPatientNameEditText;
//    private Spinner mDoctorSpinner;
//    private EditText mDateEditText;
//    private EditText mTimeEditText;
//    private Button mBookButton;

    Context context;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        fab = findViewById(R.id.fab); // Initialize the fab button

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
//
//        mPatientNameEditText = findViewById(R.id.editTextText);
//        mDoctorSpinner = findViewById(R.id.spinner);
//        mDateEditText = findViewById(R.id.ed);
//        mTimeEditText = findViewById(R.id.editTextTime);
//        mBookButton = findViewById(R.id.button);
//
//        mBookButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bookAppointment();
//            }
//        });
//    }
//
//    private void bookAppointment() {
//        String patientName = mPatientNameEditText.getText().toString();
//        String selectedDoctor = mDoctorSpinner.getSelectedItem().toString();
//        String date = mDateEditText.getText().toString();
//        String time = mTimeEditText.getText().toString();
//
//        if (patientName.isEmpty() || selectedDoctor.isEmpty() || date.isEmpty() || time.isEmpty()) {
//            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Here you'll add code to handle the appointment booking logic,
//        // such as sending data to a server or saving it in a database.
//        // ...
//
//        Toast.makeText(this, "Appointment booked!", Toast.LENGTH_SHORT).show();
//    }
//}




