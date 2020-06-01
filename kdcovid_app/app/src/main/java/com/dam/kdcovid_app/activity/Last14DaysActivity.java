package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class Last14DaysActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderLast14;
    private TextView tvHeaderFeelOkLast14;
    private RadioButton rdbWentOutOfCity;
    private RadioButton rdbContactWithOutsider;
    private RadioButton rdbContactWithInfected;
    private RadioButton rdbLast14DaysNOA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last14_days);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        rdbWentOutOfCity = findViewById(R.id.rdbWentOutOfCity);
        rdbContactWithOutsider = findViewById(R.id.rdbContactWithOutsider);
        rdbContactWithInfected = findViewById(R.id.rdbContactWithInfected);
        rdbLast14DaysNOA = findViewById(R.id.rdbLast14DaysNOA);
        tvHeaderFeelOkLast14 = findViewById(R.id.tvHeaderFeelOkLast14);
        tvHeaderLast14 = findViewById(R.id.tvHeaderLast14);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderLast14.setVisibility(View.GONE);
            tvHeaderFeelOkLast14.setVisibility(View.VISIBLE);
        }
    }

    public void uncheckLast14DaysNOA(View view) {
        // Click event for all radiobuttons except NOA
        rdbLast14DaysNOA.setChecked(false);
    }

    public void uncheckAllButLast14DaysNOA(View view) {
        // Click event for rdbLast14DaysNOA
        rdbWentOutOfCity.setChecked(false);
        rdbContactWithOutsider.setChecked(false);
        rdbContactWithInfected.setChecked(false);
    }

    public void onclickBtnLast14DaysNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setWentOutOfCity(rdbWentOutOfCity.isChecked());
        this.patient.setHadContactWithOutsider(rdbContactWithOutsider.isChecked());
        this.patient.setHadContactWithInfected(rdbContactWithInfected.isChecked());
        this.patient.setHadLast14DaysNOA(rdbLast14DaysNOA.isChecked());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), AgeRangeActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

}
