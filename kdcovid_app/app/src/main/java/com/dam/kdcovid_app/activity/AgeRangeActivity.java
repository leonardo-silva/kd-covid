package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class AgeRangeActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderAgeRange;
    private TextView tvHeaderFeelOkAgeRange;
    private RadioButton rdb1to15Years;
    private RadioButton rdb16to30Years;
    private RadioButton rdb31to45Years;
    private RadioButton rdb46to60Years;
    private RadioButton rdb60PlusYears;
    private RadioGroup rdgAgeRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_range);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        rdb1to15Years = findViewById(R.id.rdb1to15Years);
        rdb16to30Years = findViewById(R.id.rdb16to30Years);
        rdb31to45Years = findViewById(R.id.rdb31to45Years);
        rdb46to60Years = findViewById(R.id.rdb46to60Years);
        rdb60PlusYears = findViewById(R.id.rdb60PlusYears);
        rdgAgeRange = findViewById(R.id.rdgAgeRange);
        tvHeaderAgeRange = findViewById(R.id.tvHeaderAgeRange);
        tvHeaderFeelOkAgeRange = findViewById(R.id.tvHeaderFeelOkAgeRange);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderAgeRange.setVisibility(View.GONE);
            tvHeaderFeelOkAgeRange.setVisibility(View.VISIBLE);
        }
    }

    public void onclickBtnAgeRangeNext(View view) {
        if (rdgAgeRange.getCheckedRadioButtonId() != -1) {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setAge1to15Years(rdb1to15Years.isChecked());
            this.patient.setAge16to30Years(rdb16to30Years.isChecked());
            this.patient.setAge31to45Years(rdb31to45Years.isChecked());
            this.patient.setAge46to60Years(rdb46to60Years.isChecked());
            this.patient.setAge60PlusYears(rdb60PlusYears.isChecked());
            // Call next activity
            this.gotoNextActivity();
        } else {
            Toast.makeText(AgeRangeActivity.this,
                    R.string.err_select_atleast_one_answer, Toast.LENGTH_LONG).show();
        }
    }

    private void gotoNextActivity() {
        Class<?> cls;

        if (this.patient.getHasSymptom())
            cls = PriorDiseasesActivity.class;
        else
            cls = GenderActivity.class;
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}