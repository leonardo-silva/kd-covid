package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class Feeling extends AppCompatActivity {

    private Button btnNoSymptom;
    private Button btnYesSymptom;
    private static final boolean YES = true;
    private static final boolean NO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
        // Set up visual components
        btnNoSymptom = findViewById(R.id.btnNoSymptom);
        btnYesSymptom = findViewById(R.id.btnYesSymptom);
        btnNoSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Feeling.this, "Ainda não fiz essa parte!", Toast.LENGTH_LONG).show();
            }
        });
        btnYesSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoYesSymptomActivity();
            }
        });
    }

    private void gotoYesSymptomActivity() {
        // Create Patient instance
        Patient patient = new Patient(YES);
        // Call Agreement activity
        Intent intent = new Intent(getApplicationContext(), YesSymptom.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}
