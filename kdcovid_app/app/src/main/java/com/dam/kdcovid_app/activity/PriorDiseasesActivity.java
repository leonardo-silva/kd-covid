package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class PriorDiseasesActivity extends AppCompatActivity {

    private Patient patient;
    private CheckBox chkDiabetes;
    private CheckBox chkHeartProblem;
    private CheckBox chkChronicKidney;
    private CheckBox chkChronicRespiratory;
    private CheckBox chkHighPressure;
    private CheckBox chkCancer;
    private CheckBox chkDontHavePriorDisease;
    private CheckBox chkPriorDiseaseDWA;

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
        chkDiabetes = findViewById(R.id.chkDiabetes);
        chkHeartProblem = findViewById(R.id.chkHeartProblem);
        chkChronicKidney = findViewById(R.id.chkChronicKidney);
        chkChronicRespiratory = findViewById(R.id.chkChronicRespiratory);
        chkHighPressure = findViewById(R.id.chkHighPressure);
        chkCancer = findViewById(R.id.chkCancer);
        chkDontHavePriorDisease = findViewById(R.id.chkDontHavePriorDisease);
        chkPriorDiseaseDWA = findViewById(R.id.chkPriorDiseaseDWA);
        // Checkbox control
        CompoundButton.OnCheckedChangeListener listener =
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for all checkboxes except DWA and DontHave
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            chkDontHavePriorDisease.setChecked(false);
                            chkPriorDiseaseDWA.setChecked(false);
                            //chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            //chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                };
        chkDiabetes.setOnCheckedChangeListener(listener);
        chkHeartProblem.setOnCheckedChangeListener(listener);
        chkChronicKidney.setOnCheckedChangeListener(listener);
        chkChronicRespiratory.setOnCheckedChangeListener(listener);
        chkHighPressure.setOnCheckedChangeListener(listener);
        chkCancer.setOnCheckedChangeListener(listener);

        chkDontHavePriorDisease.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for chkNOA
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            uncheckAllButDontHavePriorDisease();
                            //chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            //chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                });
        chkPriorDiseaseDWA.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Click event for chkNOA
                        CheckBox chk = ((CheckBox) buttonView);
                        if (isChecked) {
                            uncheckAllButPriorDiseaseDWA();
                            //chk.setBackgroundColor(getResources().getColor(R.color.colorSelected));
                        } else {
                            //chk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                });
    }

    private void uncheckAllButDontHavePriorDisease() {
        // Part of the Click event for chkDontHavePriorDisease
        chkDiabetes.setChecked(false);
        chkHeartProblem.setChecked(false);
        chkChronicKidney.setChecked(false);
        chkChronicRespiratory.setChecked(false);
        chkHighPressure.setChecked(false);
        chkCancer.setChecked(false);
        chkPriorDiseaseDWA.setChecked(false);
    }

    private void uncheckAllButPriorDiseaseDWA() {
        // Click event for chkPriorDiseaseDWA
        chkDiabetes.setChecked(false);
        chkHeartProblem.setChecked(false);
        chkChronicKidney.setChecked(false);
        chkChronicRespiratory.setChecked(false);
        chkHighPressure.setChecked(false);
        chkCancer.setChecked(false);
        chkDontHavePriorDisease.setChecked(false);
    }

    public void onclickBtnPriorDiseasesNext(View view) {
        if (this.answerSelected()) {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setHasDiabetes(chkDiabetes.isChecked());
            this.patient.setHasHeartProblem(chkHeartProblem.isChecked());
            this.patient.setHasChronicKidney(chkChronicKidney.isChecked());
            this.patient.setHasChronicRespiratory(chkChronicRespiratory.isChecked());
            this.patient.setHasHighPressure(chkHighPressure.isChecked());
            this.patient.setHasCancer(chkCancer.isChecked());
            this.patient.setDontHavePriorDisease(chkDontHavePriorDisease.isChecked());
            this.patient.setPriorDiseasesDWA(chkPriorDiseaseDWA.isChecked());
            // Call next activity
            this.gotoNextActivity();
        } else {
            Toast.makeText(PriorDiseasesActivity.this,
                    R.string.err_select_atleast_one_answer, Toast.LENGTH_LONG).show();
        }
    }

    private boolean answerSelected() {
        return (
                chkDiabetes.isChecked() ||
                chkHeartProblem.isChecked() ||
                chkChronicKidney.isChecked() ||
                chkChronicRespiratory.isChecked() ||
                chkHighPressure.isChecked() ||
                chkCancer.isChecked() ||
                chkDontHavePriorDisease.isChecked() ||
                chkPriorDiseaseDWA.isChecked()
        );
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), GenderActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

}
