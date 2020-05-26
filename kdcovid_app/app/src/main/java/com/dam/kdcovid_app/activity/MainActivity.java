package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.dam.kdcovid_app.R;

/*
    Main activity, initial screen
    @author Leonardo Silva
*/

public class MainActivity extends AppCompatActivity {

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // To hide the title bar
        //getSupportActionBar().hide();

        // Set up visual components
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });

    }

    protected void nextActivity() {
        // Call Agreement activity
        Intent intent = new Intent(getApplicationContext(), Agreement.class);
        startActivity(intent);
    }
}
