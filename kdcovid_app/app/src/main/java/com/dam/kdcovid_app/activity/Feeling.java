package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dam.kdcovid_app.R;

public class Feeling extends AppCompatActivity {

    private Button btnNoSymptom;
    private Button btnYesSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
        // To hide the title bar
        getSupportActionBar().hide();
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
                Toast.makeText(Feeling.this, "Ainda não fiz essa parte!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
