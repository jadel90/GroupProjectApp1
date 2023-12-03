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
    private RadioButton radioButtonYesPains, radioButtonNoPains;

    private RadioButton radioButtonYesRN, radioButtonNoRN;
    private RadioButton radioButtonYesST, radioButtonNoST;
    private RadioButton radioButtonYesTiredness, radioButtonNoTiredness;
    private Spinner ageRangeSpinner, specificAgeSpinner;
    private Button submitButton;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asthma_questionnaire);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize UI components
        initializeUIComponents();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });

        // Set up spinners with data
        setupAgeRangeSpinner();
    }


    private boolean isValidAgeRange(String ageRange) {
        // Implement the logic to check if the selected age range is valid
        return true;
    }

    private void initializeUIComponents() {
        // Initialize RadioButtons for Asthma
        radioButtonYesAsthma = findViewById(R.id.radioButtonYesAsthma);
        radioButtonNoAsthma = findViewById(R.id.radioButtonNoAsthma);

        // Initialize RadioButtons for Difficulty Breathing
        radioButtonYesDB = findViewById(R.id.radioButtonYesDB);
        radioButtonNoDB = findViewById(R.id.radioButtonNoDB);

        // Initialize RadioButtons for Dry Cough
        radioButtonYesDC = findViewById(R.id.radioButtonYesDC);
        radioButtonNoDC = findViewById(R.id.radioButtonNoDC);

        // Initialize RadioButtons for Nasal Congestion
        radioButtonYesNC = findViewById(R.id.radioButtonYesNC);
        radioButtonNoNC = findViewById(R.id.radioButtonNoNC);

        radioButtonYesPains = findViewById(R.id.radioButtonYesPains);
        radioButtonNoPains = findViewById(R.id.radioButtonNoPains);

        // Initialize RadioButtons for Runny Nose
        radioButtonYesRN = findViewById(R.id.radioButtonYesRN);
        radioButtonNoRN = findViewById(R.id.radioButtonNoRN);

        // Initialize RadioButtons for Sore Throat
        radioButtonYesST = findViewById(R.id.radioButtonYesSoreThroat);
        radioButtonNoST = findViewById(R.id.radioButtonNoSoreThroat);

        // Initialize RadioButtons for Tiredness
        radioButtonYesTiredness = findViewById(R.id.radioButtonYesTiredness);
        radioButtonNoTiredness = findViewById(R.id.radioButtonNoTiredness);

        // Initialize Spinners for Age Range and Specific Age
        ageRangeSpinner = findViewById(R.id.age_range_spinner);
        specificAgeSpinner = findViewById(R.id.specific_age_spinner); // Ensure this is defined in your layout

        // Initialize Submit Button
        submitButton = findViewById(R.id.submitButton);
    }

    private void clearForm() {
        radioButtonYesDB.setChecked(false);
        radioButtonNoDB.setChecked(false);
        radioButtonYesDC.setChecked(false);
        radioButtonNoDC.setChecked(false);
        radioButtonYesNC.setChecked(false);
        radioButtonNoNC.setChecked(false);
        radioButtonYesRN.setChecked(false);
        radioButtonNoRN.setChecked(false);
        radioButtonYesST.setChecked(false);
        radioButtonNoST.setChecked(false);
        radioButtonYesTiredness.setChecked(false);
        radioButtonNoTiredness.setChecked(false);
        ageRangeSpinner.setSelection(0);
        specificAgeSpinner.setSelection(0);
    }

    private void setupAgeRangeSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Select Age Range", "0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60+"});
        ageRangeSpinner.setAdapter(adapter);
    }

    private void updateSpecificAgeSpinner(int ageRangePosition) {
        String[] ages;
        switch (ageRangePosition) {
            case 1: // 0-9
                ages = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                break;
            case 2: // 10-19
                ages = new String[]{"10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
                break;
            case 3: // 20-29
                ages = new String[]{"20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
                break;
            case 4: // 30-39
                ages = new String[]{"30", "31", "32", "33", "34", "35", "36", "37", "38", "39"};
                break;
            case 5: // 40-49
                ages = new String[]{"40", "41", "42", "43", "44", "45", "46", "47", "48", "49"};
                break;
            case 6: // 50-59
                ages = new String[]{"50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
                break;
            case 7: // 60+
                ages = new String[]{"60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70+"};
                break;
            default:
                ages = new String[]{};
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ages);
        specificAgeSpinner.setAdapter(adapter);
    }

    private void submitData() {
        if (!isValidAgeRange(ageRangeSpinner.getSelectedItem().toString())) {
            Toast.makeText(Asthma_Questionnaire.this, "Age range not in valid range", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("hasAsthma", radioButtonYesAsthma.isChecked());
        data.put("hasBreathingDifficulty", radioButtonYesDB.isChecked());
        data.put("hasDryCough", radioButtonYesDC.isChecked());
        data.put("hasNasalCongestion", radioButtonYesNC.isChecked());
        data.put("hasPains", radioButtonYesPains.isChecked());
        data.put("hasRunnyNose", radioButtonYesRN.isChecked());
        data.put("hasSoreThroat", radioButtonYesST.isChecked());
        data.put("isTired", radioButtonYesTiredness.isChecked());
        data.put("ageRange", ageRangeSpinner.getSelectedItem().toString());
        data.put("specificAge", specificAgeSpinner.getSelectedItem().toString());

        firestore.collection("questionnaires")
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Asthma_Questionnaire.this, "Data stored in Firestore", Toast.LENGTH_SHORT).show();
                            clearForm();
                        } else {
                            Toast.makeText(Asthma_Questionnaire.this, "Error storing data in Firestore", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
