package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class PriorDiseasesActivity extends AppCompatActivity {

    private Patient patient;
    private RadioButton rdbDiabetes;
    private RadioButton rdbHeartProblem;
    private RadioButton rdbChronicKidney;
    private RadioButton rdbChronicRespiratory;
    private RadioButton rdbHighPressure;
    private RadioButton rdbCancer;
    private RadioButton rdbDontHavePriorDisease;
    private RadioButton rdbPriorDiseaseDWA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prior_diseases);
        // Set up visual components
        this.setUpViewById();
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
    }

    private void setUpViewById() {
        rdbDiabetes = findViewById(R.id.rdbDiabetes);
        rdbHeartProblem = findViewById(R.id.rdbHeartProblem);
        rdbChronicKidney = findViewById(R.id.rdbChronicKidney);
        rdbChronicRespiratory = findViewById(R.id.rdbChronicRespiratory);
        rdbHighPressure = findViewById(R.id.rdbHighPressure);
        rdbCancer = findViewById(R.id.rdbCancer);
        rdbDontHavePriorDisease = findViewById(R.id.rdbDontHavePriorDisease);
        rdbPriorDiseaseDWA = findViewById(R.id.rdbPriorDiseaseDWA);
    }

    public void uncheckAllButDontHavePriorDisease(View view) {
        // Click event for rdbDontHavePriorDisease
        rdbDiabetes.setChecked(false);
        rdbHeartProblem.setChecked(false);
        rdbChronicKidney.setChecked(false);
        rdbChronicRespiratory.setChecked(false);
        rdbHighPressure.setChecked(false);
        rdbCancer.setChecked(false);
        rdbPriorDiseaseDWA.setChecked(false);
    }

    public void uncheckAllButPriorDiseaseDWA(View view) {
        // Click event for rdbPriorDiseaseDWA
        rdbDiabetes.setChecked(false);
        rdbHeartProblem.setChecked(false);
        rdbChronicKidney.setChecked(false);
        rdbChronicRespiratory.setChecked(false);
        rdbHighPressure.setChecked(false);
        rdbCancer.setChecked(false);
        rdbDontHavePriorDisease.setChecked(false);
    }

    public void uncheckRDBDiseases(View view) {
        // Click event for all radiobuttons except rdbDontHavePriorDisease and rdbPriorDiseaseDWA
        rdbDontHavePriorDisease.setChecked(false);
        rdbPriorDiseaseDWA.setChecked(false);
    }

    public void onclickBtnPriorDiseasesNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setHasDiabetes(rdbDiabetes.isChecked());
        this.patient.setHasHeartProblem(rdbHeartProblem.isChecked());
        this.patient.setHasChronicKidney(rdbChronicKidney.isChecked());
        this.patient.setHasChronicRespiratory(rdbChronicRespiratory.isChecked());
        this.patient.setHasHighPressure(rdbHighPressure.isChecked());
        this.patient.setHasCancer(rdbCancer.isChecked());
        this.patient.setDontHavePriorDisease(rdbDontHavePriorDisease.isChecked());
        this.patient.setPriorDiseasesDWA(rdbPriorDiseaseDWA.isChecked());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        //Intent intent = new Intent(getApplicationContext(), AgeRangeActivity.class);
        //intent.putExtra("patient", patient);
        //startActivity(intent);
        Toast.makeText(PriorDiseasesActivity.this, "Ainda não fiz essa parte!", Toast.LENGTH_LONG).show();
    }

}
