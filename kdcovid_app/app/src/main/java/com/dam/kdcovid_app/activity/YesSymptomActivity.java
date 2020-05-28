package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class YesSymptomActivity extends AppCompatActivity {

    private Patient patient;
    private RadioButton rdbFever;
    private RadioButton rdbRunningNose;
    private RadioButton rdbTiredness;
    private RadioButton rdbCough;
    private RadioButton rdbBreathProblem;
    private RadioButton rdbPurpleMouth;
    private RadioButton rdbSoreThroat;
    private RadioButton rdbChestPressure;
    private RadioButton rdbNOA;
    //private Button btnYesSymptomNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_symptom);
        // Set up visual components
        this.setUpViewById();
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
    }

    public void onclickBtnYesSymptomNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setHasFever(rdbFever.isChecked());
        this.patient.setHasRunningNose(rdbRunningNose.isChecked());
        this.patient.setHasTiredness(rdbTiredness.isChecked());
        this.patient.setHasCough(rdbCough.isChecked());
        this.patient.setHasBreathProblem(rdbBreathProblem.isChecked());
        this.patient.setHasPurpleMouth(rdbPurpleMouth.isChecked());
        this.patient.setHasSoreThroat(rdbSoreThroat.isChecked());
        this.patient.setHasChestPressure(rdbChestPressure.isChecked());
        this.patient.setHasNOASymptom(rdbNOA.isChecked());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), SymptomDurationActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    public void uncheckNOA(View view) {
        // Click event for all radiobuttons except NOA
        rdbNOA.setChecked(false);
    }

    public void uncheckAllRadioButtonsButNOA(View view) {
        // Click event for rdbNOA
        rdbFever.setChecked(false);
        rdbRunningNose.setChecked(false);
        rdbTiredness.setChecked(false);
        rdbCough.setChecked(false);
        rdbBreathProblem.setChecked(false);
        rdbPurpleMouth.setChecked(false);
        rdbSoreThroat.setChecked(false);
        rdbChestPressure.setChecked(false);
    }

    private void setUpViewById() {
        rdbFever = findViewById(R.id.rdbFever);
        rdbRunningNose = findViewById(R.id.rdbRunningNose);
        rdbTiredness = findViewById(R.id.rdbTiredness);
        rdbCough = findViewById(R.id.rdbCough);
        rdbBreathProblem = findViewById(R.id.rdbBreathProblem);
        rdbPurpleMouth = findViewById(R.id.rdbPurpleMouth);
        rdbSoreThroat = findViewById(R.id.rdbSoreThroat);
        rdbChestPressure = findViewById(R.id.rdbChestPressure);
        rdbNOA = findViewById(R.id.rdbNOA);
        //btnYesSymptomNext = findViewById(R.id.btnYesSymptom);
    }

}
