package com.ifnmg.dmelab.kdcovid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.ifnmg.dmelab.kdcovid.R;

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
        // Set up visual components
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Get parameter (if exists)
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int exit_code = (int)bundle.get("exit");
            if (exit_code == 1) {
                // bundle has exit code (see ResultActivity)
                this.finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void nextActivity() {
        // Call AgreementActivity activity
        Intent intent = new Intent(getApplicationContext(), AgreementActivity.class);
        startActivity(intent);
    }
}
