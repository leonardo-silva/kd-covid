package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dam.kdcovid_app.R;

public class Agreement extends AppCompatActivity {

    private Button btnIAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        // To hide the title bar
        getSupportActionBar().hide();
        // Set up visual components
        btnIAgree = findViewById(R.id.btnIAgree);
        btnIAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    protected void nextActivity() {
        // Call Agreement activity
        Intent intent = new Intent(getApplicationContext(), Feeling.class);
        startActivity(intent);
    }
}
