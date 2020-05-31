package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class GenderActivity extends AppCompatActivity {

    private Patient patient;
    private RadioButton rdbMale;
    private RadioButton rdbFemale;
    private RadioButton rdbOtherGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        // Set up visual components
        this.setUpViewById();
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
    }

    private void setUpViewById() {
        rdbMale = findViewById(R.id.rdbMale);
        rdbFemale = findViewById(R.id.rdbFemale);
        rdbOtherGender = findViewById(R.id.rdbOtherGender);
    }

    public void onclickBtnGenderNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setMale(rdbMale.isChecked());
        this.patient.setFemale(rdbFemale.isChecked());
        this.patient.setOtherGender(rdbOtherGender.isChecked());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), CityActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

}
