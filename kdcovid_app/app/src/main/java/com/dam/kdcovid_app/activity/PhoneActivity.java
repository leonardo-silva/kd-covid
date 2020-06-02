package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class PhoneActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderPhone;
    private TextView tvHeaderFeelOkPhone;
    private RadioGroup rdgPhone;
    private RadioButton rdbPhoneDWA;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient) bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        etPhone = findViewById(R.id.etPhone);
        rdbPhoneDWA = findViewById(R.id.rdbPhoneDWA);
        rdgPhone = findViewById(R.id.rdgPhone);

        rdgPhone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdbFillPhone) {
                    etPhone.setVisibility(View.VISIBLE);
                    etPhone.requestFocus();
                }
                else
                    etPhone.setVisibility(View.INVISIBLE);
            }
        });
        tvHeaderPhone = findViewById(R.id.tvHeaderPhone);
        tvHeaderFeelOkPhone = findViewById(R.id.tvHeaderFeelOkPhone);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderPhone.setVisibility(View.GONE);
            tvHeaderFeelOkPhone.setVisibility(View.VISIBLE);
        }
    }

    public void onclickBtnPhoneNext(View view) {
        if (rdgPhone.getCheckedRadioButtonId() != -1) {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setPhoneDWA(rdbPhoneDWA.isChecked());
            this.patient.setPhone(etPhone.getText().toString());
            // Call next activity
            this.gotoNextActivity();
        } else {
            Toast.makeText(PhoneActivity.this,
                    R.string.err_select_atleast_one_answer, Toast.LENGTH_LONG).show();
        }
    }

    private void gotoNextActivity() {
        // Call next activity
        //Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
        //intent.putExtra("patient", patient);
        //startActivity(intent);
        Toast.makeText(PhoneActivity.this, "AGORA VEM O RESULTADO!", Toast.LENGTH_LONG).show();
    }

}
