package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class YesSymptomActivity extends AppCompatActivity {

    private Patient patient;
    private CheckBox chkFever;
    private CheckBox chkRunningNose;
    private CheckBox chkTiredness;
    private CheckBox chkCough;
    private CheckBox chkBreathProblem;
    private CheckBox chkPurpleMouth;
    private CheckBox chkSoreThroat;
    private CheckBox chkChestPressure;
    private CheckBox chkNOA;
    //private Button btnYesSymptomNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_symptom);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        chkFever = findViewById(R.id.chkFever);
        chkRunningNose = findViewById(R.id.chkRunningNose);
        chkTiredness = findViewById(R.id.chkTiredness);
        chkCough = findViewById(R.id.chkCough);
        chkBreathProblem = findViewById(R.id.chkBreathProblem);
        chkPurpleMouth = findViewById(R.id.chkPurpleMouth);
        chkSoreThroat = findViewById(R.id.chkSoreThroat);
        chkChestPressure = findViewById(R.id.chkChestPressure);
        chkNOA = findViewById(R.id.chkNOA);

        CompoundButton.OnCheckedChangeListener listener =
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for all checkboxes except NOA
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            chkNOA.setChecked(false);
                            chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                };
        chkFever.setOnCheckedChangeListener(listener);
        chkRunningNose.setOnCheckedChangeListener(listener);
        chkTiredness.setOnCheckedChangeListener(listener);
        chkCough.setOnCheckedChangeListener(listener);
        chkBreathProblem.setOnCheckedChangeListener(listener);
        chkPurpleMouth.setOnCheckedChangeListener(listener);
        chkSoreThroat.setOnCheckedChangeListener(listener);
        chkChestPressure.setOnCheckedChangeListener(listener);

        chkNOA.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for chkNOA
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            uncheckAllCheckboxesButNOA();
                            chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                });
    }

    public void onclickBtnYesSymptomNext(View view) {
        if (this.answerSelected()) {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setHasFever(chkFever.isChecked());
            this.patient.setHasRunningNose(chkRunningNose.isChecked());
            this.patient.setHasTiredness(chkTiredness.isChecked());
            this.patient.setHasCough(chkCough.isChecked());
            this.patient.setHasBreathProblem(chkBreathProblem.isChecked());
            this.patient.setHasPurpleMouth(chkPurpleMouth.isChecked());
            this.patient.setHasSoreThroat(chkSoreThroat.isChecked());
            this.patient.setHasChestPressure(chkChestPressure.isChecked());
            this.patient.setHasNOASymptom(chkNOA.isChecked());
            // Call next activity
            this.gotoNextActivity();
        } else {
            Toast.makeText(YesSymptomActivity.this,
                    R.string.err_select_atleast_one_answer, Toast.LENGTH_LONG).show();
        }
    }

    private boolean answerSelected() {
        return (
                chkFever.isChecked() ||
                chkRunningNose.isChecked() ||
                chkTiredness.isChecked() ||
                chkCough.isChecked() ||
                chkBreathProblem.isChecked() ||
                chkPurpleMouth.isChecked() ||
                chkSoreThroat.isChecked() ||
                chkChestPressure.isChecked() ||
                chkNOA.isChecked()
        );
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), SymptomDurationActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    /*
    public void uncheckNOA(View view) {
        // Click event for all checkboxes except NOA
        chkNOA.setChecked(false);

        CheckBox chk = ((CheckBox) view);
        if (chk.isChecked()) {
            chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
        } else {
            chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

     */

    private void uncheckAllCheckboxesButNOA() {
        // Click event for chkNOA
        chkFever.setChecked(false);
        chkRunningNose.setChecked(false);
        chkTiredness.setChecked(false);
        chkCough.setChecked(false);
        chkBreathProblem.setChecked(false);
        chkPurpleMouth.setChecked(false);
        chkSoreThroat.setChecked(false);
        chkChestPressure.setChecked(false);
    }

}
