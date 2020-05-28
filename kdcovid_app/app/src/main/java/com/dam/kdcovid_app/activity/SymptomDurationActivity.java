package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class SymptomDurationActivity extends AppCompatActivity {

    private Patient patient;
    private RadioButton rdb1to3Days;
    private RadioButton rdb4to7Days;
    private RadioButton rdb8to10Days;
    private RadioButton rdb11to14Days;
    private RadioButton rdb14PlusDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_duration);
        // Set up visual components
        this.setUpViewById();
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
    }
    private void setUpViewById() {
        rdb1to3Days = findViewById(R.id.rdb1to3Days);
        rdb4to7Days = findViewById(R.id.rdb4to7Days);
        rdb8to10Days = findViewById(R.id.rdb8to10Days);
        rdb11to14Days = findViewById(R.id.rdb11to14Days);
        rdb14PlusDays = findViewById(R.id.rdb14PlusDays);
    }
    
    public void onclickBtnSymptomDurationNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setDuration1to3Days(rdb1to3Days.isChecked());
        this.patient.setDuration4to7Days(rdb4to7Days.isChecked());
        this.patient.setDuration8to10Days(rdb8to10Days.isChecked());
        this.patient.setDuration11to14Days(rdb11to14Days.isChecked());
        this.patient.setDuration14PlusDays(rdb14PlusDays.isChecked());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), Last14DaysActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}