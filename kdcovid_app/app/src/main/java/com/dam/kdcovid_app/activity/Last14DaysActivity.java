package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class Last14DaysActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderLast14;
    private TextView tvHeaderFeelOkLast14;
    private CheckBox chkWentOutOfCity;
    private CheckBox chkContactWithOutsider;
    private CheckBox chkContactWithInfected;
    private CheckBox chkLast14DaysNOA;

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
        chkWentOutOfCity = findViewById(R.id.chkWentOutOfCity);
        chkContactWithOutsider = findViewById(R.id.chkContactWithOutsider);
        chkContactWithInfected = findViewById(R.id.chkContactWithInfected);
        chkLast14DaysNOA = findViewById(R.id.chkLast14DaysNOA);
        tvHeaderFeelOkLast14 = findViewById(R.id.tvHeaderFeelOkLast14);
        tvHeaderLast14 = findViewById(R.id.tvHeaderLast14);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderLast14.setVisibility(View.GONE);
            tvHeaderFeelOkLast14.setVisibility(View.VISIBLE);
        }
        CompoundButton.OnCheckedChangeListener listener =
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for all checkboxes except NOA
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            chkLast14DaysNOA.setChecked(false);
                            chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                };
        chkWentOutOfCity.setOnCheckedChangeListener(listener);
        chkContactWithOutsider.setOnCheckedChangeListener(listener);
        chkContactWithInfected.setOnCheckedChangeListener(listener);

        chkLast14DaysNOA.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for chkNOA
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            uncheckAllButLast14DaysNOA();
                            chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                });
    }

    //public void uncheckLast14DaysNOA(View view) {
    //    // Click event for all checkboxes except NOA
    //    chkLast14DaysNOA.setChecked(false);
   // }

    private void uncheckAllButLast14DaysNOA() {
        // Click event for chkLast14DaysNOA
        chkWentOutOfCity.setChecked(false);
        chkContactWithOutsider.setChecked(false);
        chkContactWithInfected.setChecked(false);
    }

    public void onclickBtnLast14DaysNext(View view) {
        if (this.answerSelected()) {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setWentOutOfCity(chkWentOutOfCity.isChecked());
            this.patient.setHadContactWithOutsider(chkContactWithOutsider.isChecked());
            this.patient.setHadContactWithInfected(chkContactWithInfected.isChecked());
            this.patient.setHadLast14DaysNOA(chkLast14DaysNOA.isChecked());
            // Call next activity
            this.gotoNextActivity();
        } else {
            Toast.makeText(Last14DaysActivity.this,
                    R.string.err_select_atleast_one_answer, Toast.LENGTH_LONG).show();
        }
    }

    private boolean answerSelected() {
        return (
                chkWentOutOfCity.isChecked() ||
                chkContactWithOutsider.isChecked() ||
                chkContactWithInfected.isChecked() ||
                chkLast14DaysNOA.isChecked()
        );
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), AgeRangeActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

}
