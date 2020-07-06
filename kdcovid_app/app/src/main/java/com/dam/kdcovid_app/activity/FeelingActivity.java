package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class FeelingActivity extends AppCompatActivity {

    private Patient patient;
    private Button btnFeelingNext;
    private RadioGroup rdgFeeling;
    private RadioButton rdbNoSymptom;
    //private RadioButton rdbYesSymptom;
    private static final boolean YES = true;
    private static final boolean NO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
        // Set up visual components
        btnFeelingNext = findViewById(R.id.btnFeelingNext);
        rdgFeeling = findViewById(R.id.rdgFeeling);
        rdbNoSymptom = findViewById(R.id.rdbNoSymptom);
        //rdbYesSymptom = findViewById(R.id.rdbYesSymptom);

        btnFeelingNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdgFeeling.getCheckedRadioButtonId() != -1) {
                    // Transfer the answer to the Patient object before proceeding and Call next activity
                    if (rdbNoSymptom.isChecked()) {
                        gotoLast14DaysActivity();
                    } else {
                        gotoYesSymptomActivity();
                    }
                } else {
                    Toast.makeText(FeelingActivity.this,
                            R.string.err_select_atleast_one_answer, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void gotoYesSymptomActivity() {
        // Create Patient instance
        this.patient.setHasSymptom(YES);
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), YesSymptomActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    private void gotoLast14DaysActivity() {
        // Create Patient instance
        this.patient.setHasSymptom(NO);
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), Last14DaysActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

}
