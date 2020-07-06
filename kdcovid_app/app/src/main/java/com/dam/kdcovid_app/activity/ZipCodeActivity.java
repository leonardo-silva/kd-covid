package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class ZipCodeActivity extends AppCompatActivity {

    private Patient patient;
    //private TextView tvHeaderZipCode;
    //private TextView tvHeaderFeelOkZipCode;
    private TextView tvZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient) bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        tvZipCode = findViewById(R.id.etZipCode);

        // Brazilian cell phone mask
        SimpleMaskFormatter smf =
                new SimpleMaskFormatter(getResources().getString(R.string.zip_code_mask));
        MaskTextWatcher mtw = new MaskTextWatcher(tvZipCode, smf);
        tvZipCode.addTextChangedListener(mtw);

        //tvHeaderZipCode = findViewById(R.id.tvHeaderZipCode);
        //tvHeaderFeelOkZipCode = findViewById(R.id.tvHeaderFeelOkZipCode);
        // Control header
        //if (! this.patient.getHasSymptom()) {
        //    tvHeaderZipCode.setVisibility(View.GONE);
        //    tvHeaderFeelOkZipCode.setVisibility(View.VISIBLE);
        //}
    }

    public void onclickBtnZipCodeNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setZipCode(tvZipCode.getText().toString());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), FullNameActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
        //Toast.makeText(NeighborhoodActivity.this, this.patient.getNeighborhoodName(), Toast.LENGTH_LONG).show();
    }
}