package com.example.groupprojectapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Lung_Cancer_Questionnaire extends AppCompatActivity {

    private EditText ageEditText, alcoholEditText, allergyEditText, breathShortnessEditText,
            chestPainEditText, chronicDiseaseEditText, coughingEditText, fatigueEditText,
            peerPressureEditText, smokingEditText, swallowingDifficultyEditText, wheezingEditText, yellowFingersEditText;
    private RadioGroup genderRadioGroup;
    private Button submitButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lung_cancer_questionnaire);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        ageEditText = findViewById(R.id.age);
        alcoholEditText = findViewById(R.id.alcohol_consumption);
        allergyEditText = findViewById(R.id.allergy);
        breathShortnessEditText = findViewById(R.id.breath_shortness);
        chestPainEditText = findViewById(R.id.chest_pain);
        chronicDiseaseEditText = findViewById(R.id.chronic_disease);
        coughingEditText = findViewById(R.id.coughing);
        fatigueEditText = findViewById(R.id.fatigue);
        peerPressureEditText = findViewById(R.id.peer_pressure);
        smokingEditText = findViewById(R.id.smoking);
        swallowingDifficultyEditText = findViewById(R.id.swallowing_difficulty);
        wheezingEditText = findViewById(R.id.wheezing);
        yellowFingersEditText = findViewById(R.id.yellow_fingers);

        genderRadioGroup = findViewById(R.id.gender_group);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from UI elements
                int age = Integer.parseInt(ageEditText.getText().toString());
                String alcoholConsumption = alcoholEditText.getText().toString();
                String allergy = allergyEditText.getText().toString();
                String breathShortness = breathShortnessEditText.getText().toString();
                String chestPain = chestPainEditText.getText().toString();
                String chronicDisease = chronicDiseaseEditText.getText().toString();
                String coughing = coughingEditText.getText().toString();
                String fatigue = fatigueEditText.getText().toString();
                String peerPressure = peerPressureEditText.getText().toString();
                String smoking = smokingEditText.getText().toString();
                String swallowingDifficulty = swallowingDifficultyEditText.getText().toString();
                String wheezing = wheezingEditText.getText().toString();
                String yellowFingers = yellowFingersEditText.getText().toString();

                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
                String gender = selectedGenderRadioButton.getText().toString();

                // Create an instance of LungCancerData with the retrieved values
                LungCancerData lungCancerData = new LungCancerData(age, alcoholConsumption, allergy, breathShortness,
                        chestPain, chronicDisease, coughing, fatigue, gender, peerPressure, smoking,
                        swallowingDifficulty, wheezing, yellowFingers);

                // Store the data in Firestore
                db.collection("lung_cancer_data")
                        .add(lungCancerData)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Lung_Cancer_Questionnaire.this,
                                            "Data submitted successfully!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Lung_Cancer_Questionnaire.this,
                                            "Error submitting data.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}

