package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.dam.kdcovid_app.R;

/*
    Main activity, initial screen
    @author Leonardo Silva
*/

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    //private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up visual components
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
        //tvWelcome = findViewById(R.id.tvWelcome);
        //tvWelcome.setText(Html.fromHtml(getString(R.string.welcome_msg)));
    }

    protected void nextActivity() {
        // Call AgreementActivity activity
        Intent intent = new Intent(getApplicationContext(), AgreementActivity.class);
        startActivity(intent);
    }
}
