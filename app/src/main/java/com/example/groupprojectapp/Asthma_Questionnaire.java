package com.example.groupprojectapp;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Asthma_Questionnaire extends AppCompatActivity {

    private RadioButton radioButtonYesAsthma, radioButtonNoAsthma;
    private RadioButton radioButtonYesDB, radioButtonNoDB;
    private RadioButton radioButtonYesDC, radioButtonNoDC;
    private RadioButton radioButtonYesNC, radioButtonNoNC;
    private RadioButton radioButtonYesRN, radioButtonNoRN;
    private RadioButton radioButtonYesST, radioButtonNoST;
    private RadioButton radioButtonYesTiredness, radioButtonNoTiredness;
    private Spinner ageRangeSpinner, age10to19Spinner, age20to24Spinner, age25to59Spinner, ageOver60Spinner;
    private Button submitButton;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asthma_questionnaire);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize UI components
        radioButtonYesAsthma = findViewById(R.id.radioButtonYesAsthma);
        radioButtonNoAsthma = findViewById(R.id.radioButtonNoAsthma);
        radioButtonYesDB = findViewById(R.id.radioButtonYesDB);
        radioButtonNoDB = findViewById(R.id.radioButtonNoDB);
        radioButtonYesDC = findViewById(R.id.radioButtonYesDC);
        radioButtonNoDC = findViewById(R.id.radioButtonNoDC);
        radioButtonYesNC = findViewById(R.id.radioButtonYesNC);
        radioButtonNoNC = findViewById(R.id.radioButtonNoNC);
        radioButtonYesRN = findViewById(R.id.radioButtonYesRN);
        radioButtonNoRN = findViewById(R.id.radioButtonNoRN);
        radioButtonYesST = findViewById(R.id.radioButtonYesST);
        radioButtonNoST = findViewById(R.id.radioButtonNoST);
        radioButtonYesTiredness = findViewById(R.id.radioButtonYesTiredness);
        radioButtonNoTiredness = findViewById(R.id.radioButtonNoTiredness);

        ageRangeSpinner = findViewById(R.id.age_range_spinner);
        age10to19Spinner = findViewById(R.id.age_10_19_spinner);
        age20to24Spinner = findViewById(R.id.age_20_24_spinner);
        age25to59Spinner = findViewById(R.id.age_25_59_spinner);
        ageOver60Spinner = findViewById(R.id.age_over_60_spinner);

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a Firestore document with user's answers
                Map<String, Object> data = new HashMap<>();
                data.put("hasAsthma", radioButtonYesAsthma.isChecked());
                data.put("hasBreathingDifficulty", radioButtonYesDB.isChecked());
                data.put("hasDryCough", radioButtonYesDC.isChecked());
                data.put("hasNasalCongestion", radioButtonYesNC.isChecked());
                data.put("hasRunnyNose", radioButtonYesRN.isChecked());
                data.put("hasSoreThroat", radioButtonYesST.isChecked());
                data.put("isTired", radioButtonYesTiredness.isChecked());
                data.put("ageRange", ageRangeSpinner.getSelectedItem().toString());
                data.put("age10to19", age10to19Spinner.getSelectedItem().toString());
                data.put("age20to24", age20to24Spinner.getSelectedItem().toString());
                data.put("age25to59", age25to59Spinner.getSelectedItem().toString());
                data.put("ageOver60", ageOver60Spinner.getSelectedItem().toString());

                // Store the data in Firestore
                firestore.collection("questionnaires")
                        .add(data)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    // Data stored successfully
                                    Toast.makeText(Asthma_Questionnaire.this, "Data stored in Firestore", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Error occurred while storing data
                                    Toast.makeText(Asthma_Questionnaire.this, "Error storing data in Firestore", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Set up spinners with data
        setupAgeRangeSpinner();
        setupAge10to19Spinner();
        setupAge20to24Spinner();
        setupAge25to59Spinner();
        setupAgeOver60Spinner();
    }

    // Implement spinner setup methods here

    private void setupAgeRangeSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        ageRangeSpinner.setAdapter(adapter);
    }

    private void setupAge10to19Spinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"11", "12", "13", "14", "15", "16", "17", "18", "19"});
        age10to19Spinner.setAdapter(adapter);
    }

    private void setupAge20to24Spinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"20", "21", "22", "23", "24"});
        age20to24Spinner.setAdapter(adapter);
    }

    private void setupAge25to59Spinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]
                { "25", "26", "27", "28", "29",
                        "30","31","32","33","34","35","36","37","38","39",
                        "40","41", "42", "43", "44", "45", "46", "47", "48", "49",
                        "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
        age25to59Spinner.setAdapter(adapter);
    }

    private void setupAgeOver60Spinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]
                {"60", "61", "62", "63", "64", "65", "66", "67", "68", "69"});
        ageOver60Spinner.setAdapter(adapter);
    }
}

