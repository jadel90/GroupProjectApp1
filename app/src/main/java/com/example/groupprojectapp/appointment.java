package com.example.groupprojectapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class appointment extends AppCompatActivity {

    Context context;
    private Spinner spinnerDoctors;
    private EditText etDate, etTime, etReason;
    private Button btnBookAppointment;
    private DatabaseReference mDatabase;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        // Initialize Firebase Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference("appointments");

        spinnerDoctors = findViewById(R.id.spinnerDoctors);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etReason = findViewById(R.id.etReason);
        btnBookAppointment = findViewById(R.id.btnBookAppointment);


        fab = findViewById(R.id.fab); // Initialize the fab button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity4.class);
                context.startActivity(intent);
                Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show();
            }
        });


        // Set up the spinner with the array of doctor names
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.doctor_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctors.setAdapter(adapter);

        // Set up the date picker dialog
        etDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, monthOfYear, dayOfMonth) ->
                            etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1), year, month, day);
            datePickerDialog.show();
        });

        // Set up the time picker dialog
        etTime.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute1) -> {
                        String formattedTime = String.format("%02d:%02d", hourOfDay, minute1);
                        etTime.setText(formattedTime);
                    }, hour, minute, true);
            timePickerDialog.show();
        });

        // Set up the button to book the appointment
        btnBookAppointment.setOnClickListener(v -> {
            // Extract the input values
            String doctor = spinnerDoctors.getSelectedItem().toString();
            String date = etDate.getText().toString();
            String time = etTime.getText().toString();
            String reason = etReason.getText().toString();

            // Validate the input and proceed to book the appointment
            if (validateInput(date, time, doctor)) {
                bookAppointment(doctor, date, time, reason);
            }
        });
    }

    private boolean validateInput(String date, String time, String doctor) {
        if (doctor.isEmpty()) {
            Toast.makeText(this, "Please select a doctor.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (date.isEmpty()) {
            Toast.makeText(this, "Please pick a date.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (time.isEmpty()) {
            Toast.makeText(this, "Please pick a time.", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Add any other validation as needed
        return true;
    }

    private void bookAppointment(String doctor, String date, String time, String reason) {
        // Show a loading indicator
        // progressBar.setVisibility(View.VISIBLE); // Uncomment if you have a ProgressBar

        // Create a HashMap with the appointment details
        Map<String, Object> appointment = new HashMap<>();
        appointment.put("doctor", doctor);
        appointment.put("date", date);
        appointment.put("time", time);
        appointment.put("reason", reason);

        // Save the appointment to Firebase
        mDatabase.push().setValue(appointment)
                .addOnSuccessListener(aVoid -> {
                    // Hide loading indicator
                    // progressBar.setVisibility(View.GONE); // Uncomment if you have a ProgressBar

                    // Show a success message
                    Toast.makeText(appointment.this, "Appointment booked successfully!", Toast.LENGTH_LONG).show();

                    // Clear the form fields
                    clearFormFields();
                })
                .addOnFailureListener(e -> {
                    // Hide loading indicator
                    // progressBar.setVisibility(View.GONE); // Uncomment if you have a ProgressBar

                    // Show an error message
                    Toast.makeText(appointment.this, "Failed to book appointment: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }


    private void clearFormFields() {
        spinnerDoctors.setSelection(0);
        etDate.setText("");
        etTime.setText("");
        etReason.setText("");
    }






}



