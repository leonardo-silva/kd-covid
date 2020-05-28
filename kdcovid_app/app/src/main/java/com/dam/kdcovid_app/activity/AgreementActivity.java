package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dam.kdcovid_app.R;

public class AgreementActivity extends AppCompatActivity {

    private Button btnIAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
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
        // Call AgreementActivity activity
        Intent intent = new Intent(getApplicationContext(), FeelingActivity.class);
        startActivity(intent);
    }
}
