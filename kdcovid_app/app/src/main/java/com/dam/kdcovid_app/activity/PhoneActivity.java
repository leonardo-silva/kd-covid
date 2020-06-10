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
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;

import static com.dam.kdcovid_app.control.Util.isValidMail;
import static com.dam.kdcovid_app.control.Util.isValidMobile;

public class PhoneActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderPhone;
    private TextView tvHeaderFeelOkPhone;
    private TextInputEditText etEnterPhone;
    private TextInputEditText etEnterEmail;

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
        etEnterEmail = findViewById(R.id.etEnterEmail);
        etEnterPhone = findViewById(R.id.etEnterPhone);
        //etEnterPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("55"));

        // Brazilian cell phone mask
        SimpleMaskFormatter smf =
                new SimpleMaskFormatter(getResources().getString(R.string.cell_phone_mask));
        MaskTextWatcher mtw = new MaskTextWatcher(etEnterPhone, smf);
        etEnterPhone.addTextChangedListener(mtw);

        tvHeaderPhone = findViewById(R.id.tvHeaderPhone);
        tvHeaderFeelOkPhone = findViewById(R.id.tvHeaderFeelOkPhone);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderPhone.setVisibility(View.GONE);
            tvHeaderFeelOkPhone.setVisibility(View.VISIBLE);
        }
    }

    public void onclickBtnPhoneNext(View view) {
        if (! etEnterEmail.getText().toString().isEmpty() &&
            ! isValidMail(etEnterEmail.getText().toString())) {
            Toast.makeText(this, getResources().getText(R.string.err_invalid_email), Toast.LENGTH_LONG).show();

        } else if (! etEnterPhone.getText().toString().isEmpty() &&
                ! isValidMobile(etEnterPhone.getText().toString())) {
            Toast.makeText(this, getResources().getText(R.string.err_invalid_phone), Toast.LENGTH_LONG).show();

        } else if (etEnterEmail.getText().toString().isEmpty() &&
                etEnterPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getText(R.string.err_nophone_noremail), Toast.LENGTH_LONG).show();

        } else {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setEmail(etEnterEmail.getText().toString());
            this.patient.setPhone(etEnterPhone.getText().toString());
            // Call next activity
            this.gotoNextActivity();
        }
        /*
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

         */
    }

    private void gotoNextActivity() {
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
        //Toast.makeText(PhoneActivity.this, "AGORA VEM O RESULTADO!", Toast.LENGTH_LONG).show();
    }

}
