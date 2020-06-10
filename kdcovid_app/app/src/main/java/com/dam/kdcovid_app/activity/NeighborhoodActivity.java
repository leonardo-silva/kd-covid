package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class NeighborhoodActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvHeaderNeighborhood;
    private TextView tvHeaderFeelOkNeighborhood;
    private Spinner spnNeighborhood;
    private TextView tvNeighborhood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood);
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient) bundle.get("patient");
        // Set up visual components
        this.setUpViewById();
    }

    private void setUpViewById() {
        int cityArrayId = R.array.neighborhood_salinas;

        tvNeighborhood = findViewById(R.id.tvNeighborhood);
        if (this.patient.isCityJanauba()) {
            tvNeighborhood.setText(R.string.neighborhood_janauba_lbl);
            cityArrayId = R.array.neighborhood_janauba;
        }

        spnNeighborhood = findViewById(R.id.spnNeighborhood);
        // Setting up the adapter for spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, cityArrayId, R.layout.spinner_item);
        spnNeighborhood.setAdapter(adapter);

        tvHeaderNeighborhood = findViewById(R.id.tvHeaderNeighborhood);
        tvHeaderFeelOkNeighborhood = findViewById(R.id.tvHeaderFeelOkNeighborhood);
        // Control header
        if (! this.patient.getHasSymptom()) {
            tvHeaderNeighborhood.setVisibility(View.GONE);
            tvHeaderFeelOkNeighborhood.setVisibility(View.VISIBLE);
        }
    }

    public void onclickBtnNeighborhoodNext(View view) {
        Object selectedNeighborhood = spnNeighborhood.getSelectedItem();

        if (selectedNeighborhood == null ||
                selectedNeighborhood.toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.err_select_atleast_one_neighborhood), Toast.LENGTH_LONG).show();
        } else {
            // Transfer the answers to the Patient object before proceeding
            this.patient.setNeighborhoodName(selectedNeighborhood.toString());
            // Call next activity
            this.gotoNextActivity();
        }
    }

    private void gotoNextActivity() {
        // Call SymptomDurationActivity activity
        Intent intent = new Intent(getApplicationContext(), FullNameActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
        //Toast.makeText(NeighborhoodActivity.this, this.patient.getNeighborhoodName(), Toast.LENGTH_LONG).show();
    }

}