package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class FeelingActivity extends AppCompatActivity {

    private Patient patient;
    private Button btnNoSymptom;
    private Button btnYesSymptom;
    private static final boolean YES = true;
    private static final boolean NO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
        // Set up visual components
        btnNoSymptom = findViewById(R.id.btnNoSymptom);
        btnYesSymptom = findViewById(R.id.btnYesSymptom);
        btnNoSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLast14DaysActivity();
            }
        });
        btnYesSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoYesSymptomActivity();
            }
        });
    }

    private void gotoYesSymptomActivity() {
        // Create Patient instance
        this.patient = new Patient(YES);
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), YesSymptomActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    private void gotoLast14DaysActivity() {
        // Create Patient instance
        this.patient = new Patient(NO);
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), Last14DaysActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    /*
    private void setFalsePriorDiseases() {
        // Set false to all prior diseases because the patient is feeling well, and the activity
        // to collect prior diseases won't be started.
        this.patient.setHasDiabetes(false);
        this.patient.setHasHeartProblem(false);
        this.patient.setHasChronicKidney(false);
        this.patient.setHasChronicRespiratory(false);
        this.patient.setHasHighPressure(false);
        this.patient.setHasCancer(false);
        this.patient.setDontHavePriorDisease(false);
        this.patient.setPriorDiseasesDWA(false);
    }*/
}
