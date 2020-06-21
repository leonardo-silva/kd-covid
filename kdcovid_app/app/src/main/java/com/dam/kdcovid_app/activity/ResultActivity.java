package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.control.Covid19;
import com.dam.kdcovid_app.model.Patient;

public class ResultActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    private Patient patient;
    private Button btnLowRisk;
    private Button btnMediumRisk;
    private Button btnHighRisk;
    private Button btnGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient) bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
        // Get the Covid-19 result
        this.checkResult(Covid19.Screening(this.patient.generateCovid19Input()));
    }

    @Override
    public void onBackPressed() {
        // Control to press back twice to leave
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            //super.onBackPressed();
            // Call main activity
            goBackToMainActivity(true);
            return;
        } else {
            backToast = Toast.makeText(this, getResources().getString(R.string.msg_press_again_to_leave),
                    Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void checkResult(Covid19.Risk risk) {
        int result = risk.getValue();
        if (result <= Covid19.Risk.LOW.getValue()) {
            btnLowRisk.setVisibility(View.VISIBLE);
            if (result == Covid19.Risk.UNLIKELY.getValue()) {
                btnLowRisk.setText(R.string.result_low_risk1_msg);
            } else {
                btnLowRisk.setText(R.string.result_low_risk2_msg);
            }
        } else if (result <= Covid19.Risk.MEDIUM_2.getValue()) {
            btnMediumRisk.setVisibility(View.VISIBLE);
            if (result == Covid19.Risk.MEDIUM_1.getValue()) {
                btnMediumRisk.setText(R.string.result_medium_risk3_msg);
            } else {
                btnMediumRisk.setText(R.string.result_medium_risk4_msg);
            }
        } else if (result <= Covid19.Risk.HIGH_2.getValue()) {
            btnHighRisk.setVisibility(View.VISIBLE);
            if (result == Covid19.Risk.HIGH_1.getValue()) {
                btnHighRisk.setText(R.string.result_high_risk5_msg);
            } else {
                btnHighRisk.setText(R.string.result_high_risk6_msg);
            }
        } else {
            btnHighRisk.setVisibility(View.VISIBLE);
            btnHighRisk.setText(R.string.result_very_high_risk7_msg);
            btnHighRisk.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    private void setUpViewById() {
        btnLowRisk = findViewById(R.id.btnLowRisk);
        btnMediumRisk = findViewById(R.id.btnMediumRisk);
        btnHighRisk = findViewById(R.id.btnHighRisk);
        btnGoHome = findViewById(R.id.btnGoHome);

        btnLowRisk.setVisibility(View.GONE);
        btnMediumRisk.setVisibility(View.GONE);
        btnHighRisk.setVisibility(View.GONE);

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call main activity
                goBackToMainActivity(false);
            }
        });
    }

    private void goBackToMainActivity(boolean exit) {
        // Call main activity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        if (exit)
            intent.putExtra("exit", 1);
        startActivity(intent);
    }
}
