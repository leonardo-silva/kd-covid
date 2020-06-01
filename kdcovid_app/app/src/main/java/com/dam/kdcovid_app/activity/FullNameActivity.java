package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class FullNameActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderFullName;
    private TextView tvHeaderFeelOkFullName;
    private RadioGroup rdgFullName;
    private RadioButton rdbFullNameDWA;
    private EditText etFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_name);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient) bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        etFullName = findViewById(R.id.etFullName);
        rdbFullNameDWA = findViewById(R.id.rdbFullNameDWA);
        rdgFullName = findViewById(R.id.rdgFullName);

        rdgFullName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdbFillFullName) {
                    etFullName.setVisibility(View.VISIBLE);
                    etFullName.requestFocus();
                }
                else
                    etFullName.setVisibility(View.INVISIBLE);
            }
        });
        tvHeaderFullName = findViewById(R.id.tvHeaderFullName);
        tvHeaderFeelOkFullName = findViewById(R.id.tvHeaderFeelOkFullName);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderFullName.setVisibility(View.GONE);
            tvHeaderFeelOkFullName.setVisibility(View.VISIBLE);
        }
    }

    public void onclickBtnFullNameNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setFullNameDWA(rdbFullNameDWA.isChecked());
        this.patient.setFullName(etFullName.getText().toString());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
        //Toast.makeText(FullNameActivity.this, "Ainda não fiz essa parte!", Toast.LENGTH_LONG).show();
    }

}
