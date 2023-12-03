package com.example.groupprojectapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Heart_Disease_Questionnaire extends AppCompatActivity {

    private EditText ageEditText, bpEditText, chestPainEditText, cholesterolEditText, ekgResultEditText,
            exerciseAnginaEditText, fbsOver120EditText, maxHREditText, stDepressionEditText, stSlopeEditText,
            thalliumEditText, vesselsFluroNumEditText;
    private RadioGroup sexRadioGroup;
    private Button submitButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_disease_questionnaire);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        ageEditText = findViewById(R.id.age);
        bpEditText = findViewById(R.id.bp);
        chestPainEditText = findViewById(R.id.chest_pain);
        cholesterolEditText = findViewById(R.id.cholesterol);
        ekgResultEditText = findViewById(R.id.ekg_result);
        exerciseAnginaEditText = findViewById(R.id.exercise_angina);
        fbsOver120EditText = findViewById(R.id.fbs_over_120);
        maxHREditText = findViewById(R.id.max_hr);
        stDepressionEditText = findViewById(R.id.st_depression);
        stSlopeEditText = findViewById(R.id.st_slope);
        thalliumEditText = findViewById(R.id.thallium);
        vesselsFluroNumEditText = findViewById(R.id.vessels_fluro_num);
        sexRadioGroup = findViewById(R.id.sex_group);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from UI elements
                int age = Integer.parseInt(ageEditText.getText().toString());
                int bp = Integer.parseInt(bpEditText.getText().toString());
                String chestPain = chestPainEditText.getText().toString();
                int cholesterol = Integer.parseInt(cholesterolEditText.getText().toString());
                String ekgResult = ekgResultEditText.getText().toString();
                String exerciseAngina = exerciseAnginaEditText.getText().toString();
                String fbsOver120 = fbsOver120EditText.getText().toString();
                int maxHR = Integer.parseInt(maxHREditText.getText().toString());
                String sex = ((RadioButton) findViewById(sexRadioGroup.getCheckedRadioButtonId())).getText().toString();
                float stDepression = Float.parseFloat(stDepressionEditText.getText().toString());
                String stSlope = stSlopeEditText.getText().toString();
                String thallium = thalliumEditText.getText().toString();
                int vesselsFluroNum = Integer.parseInt(vesselsFluroNumEditText.getText().toString());

                // Create an instance of your data model
                HeartDiseaseData data = new HeartDiseaseData();
                data.setAge(age);
                data.setBp(bp);
                data.setChestPain(chestPain);
                data.setCholesterol(cholesterol);
                data.setEkgResult(ekgResult);
                data.setExerciseAngina(exerciseAngina);
                data.setFbsOver120(fbsOver120);
                data.setMaxHR(maxHR);
                data.setSex(sex);
                data.setStDepression(stDepression);
                data.setStSlope(stSlope);
                data.setThallium(thallium);
                data.setVesselsFluroNum(vesselsFluroNum);

                // Save data to Firestore
                saveDataToFirestore(data);
            }
        });
    }

    private void saveDataToFirestore(HeartDiseaseData data) {
        // Create a map from your data model
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("age", data.getAge());
        dataMap.put("bp", data.getBp());
        dataMap.put("chestPain", data.getChestPain());
        dataMap.put("cholesterol", data.getCholesterol());
        dataMap.put("ekgResult", data.getEkgResult());
        dataMap.put("exerciseAngina", data.getExerciseAngina());
        dataMap.put("fbsOver120", data.getFbsOver120());
        dataMap.put("maxHR", data.getMaxHR());
        dataMap.put("sex", data.getSex());
        dataMap.put("stDepression", data.getStDepression());
        dataMap.put("stSlope", data.getStSlope());
        dataMap.put("thallium", data.getThallium());
        dataMap.put("vesselsFluroNum", data.getVesselsFluroNum());

        // Add the data to Firestore
        db.collection("heart_disease_data")
                .add(dataMap)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Data saved successfully
                            // You can show a success message or perform any other actions here
                        } else {
                            // Handle the error
                        }
                    }
                });
    }
}


