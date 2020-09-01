package com.ifnmg.dmelab.kdcovid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ifnmg.dmelab.kdcovid.R;
import com.ifnmg.dmelab.kdcovid.model.Patient;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;

public class NeighborhoodActivity extends AppCompatActivity {

    private Patient patient;
    private Spinner spnCity;
    private TextView tvCity;
    private Spinner spnNeighborhood;
    private TextView tvNeighborhood;
    private String cityJanauba;
    private String citySalinas;
    private String otherCity;
    private TextInputEditText etZipCodeOtherCity;

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
        cityJanauba = getResources().getString(R.string.janauba_lbl);
        citySalinas = getResources().getString(R.string.salinas_lbl);
        otherCity = getResources().getString(R.string.other_city_lbl);

        etZipCodeOtherCity = findViewById(R.id.etZipCodeOtherCity);
        // Brazilian zip code mask
        SimpleMaskFormatter smf =
                new SimpleMaskFormatter(getResources().getString(R.string.zip_code_mask));
        MaskTextWatcher mtw = new MaskTextWatcher(etZipCodeOtherCity, smf);
        etZipCodeOtherCity.addTextChangedListener(mtw);

        tvNeighborhood = findViewById(R.id.tvNeighborhood);
        spnNeighborhood = findViewById(R.id.spnNeighborhood);
        /*
        tvNeighborhood = findViewById(R.id.tvNeighborhood);
        if (this.patient.isCityJanauba()) {
            tvNeighborhood.setText(R.string.neighborhood_janauba_lbl);
            neighborhoodArrayId = R.array.neighborhood_janauba;
        }
        */

        int cityArrayId = R.array.cities;

        spnCity = findViewById(R.id.spnCity);
        // Setting up the adapter for city spinner
        ArrayAdapter<CharSequence> adapterCity =
                ArrayAdapter.createFromResource(this, cityArrayId, R.layout.spinner_item);
        spnCity.setAdapter(adapterCity);

        spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName = parent.getItemAtPosition(position).toString();
                if ((cityName.equalsIgnoreCase(citySalinas)) ||
                    (cityName.equalsIgnoreCase(cityJanauba))) {
                    tvNeighborhood.setVisibility(View.VISIBLE);
                    spnNeighborhood.setVisibility(View.VISIBLE);
                    etZipCodeOtherCity.setVisibility(View.GONE);
                    etZipCodeOtherCity.setText("");
                    // Set up neighborhood if city is Salinas or Janaúba
                    setUpNeighborhood(cityName);
                } else if (cityName.equalsIgnoreCase(otherCity)) {
                    tvNeighborhood.setVisibility(View.GONE);
                    spnNeighborhood.setVisibility(View.GONE);
                    etZipCodeOtherCity.setVisibility(View.VISIBLE);
                } else {
                    tvNeighborhood.setVisibility(View.GONE);
                    spnNeighborhood.setVisibility(View.GONE);
                    etZipCodeOtherCity.setVisibility(View.GONE);
                    etZipCodeOtherCity.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void setUpNeighborhood(String cityName) {
        int neighborhoodArrayId = R.array.neighborhood_salinas;
        String neighborhood_lbl = getResources().getString(R.string.neighborhood_salinas_lbl);

        if (cityName.equalsIgnoreCase(cityJanauba)) {
            neighborhood_lbl = getResources().getString(R.string.neighborhood_janauba_lbl);
            //tvNeighborhood.setText(getResources().getString(R.string.neighborhood_janauba_lbl));
            neighborhoodArrayId = R.array.neighborhood_janauba;
        }
        tvNeighborhood.setText(neighborhood_lbl);

        // Setting up the adapter for neighborhood spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, neighborhoodArrayId, R.layout.spinner_item);
        spnNeighborhood.setAdapter(adapter);
    }

    public void onclickBtnNeighborhoodNext(View view) {
        Object selectedCity = spnCity.getSelectedItem();

        if (selectedCity == null ||
                selectedCity.toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.err_select_atleast_one_city), Toast.LENGTH_LONG).show();
        } else {
            String cityName = selectedCity.toString();
            if ((cityName.equalsIgnoreCase(citySalinas)) || (cityName.equalsIgnoreCase(cityJanauba))) {
                Object selectedNeighborhood = spnNeighborhood.getSelectedItem();
                if (selectedNeighborhood == null ||
                        selectedNeighborhood.toString().isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.err_select_atleast_one_neighborhood), Toast.LENGTH_LONG).show();
                } else {
                    // Transfer the answers to the Patient object before proceeding
                    this.patient.setCityName(cityName);
                    this.patient.setNeighborhoodName(selectedNeighborhood.toString());
                    this.patient.setOtherCity(false);
                    this.patient.setZipCode("");
                    // Call next activity
                    this.gotoNextActivity();
                }
            } else if (cityName.equalsIgnoreCase(otherCity)) {
                String zipcode = etZipCodeOtherCity.getText().toString();
                if (zipcode.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.err_select_atleast_one_zipcode), Toast.LENGTH_LONG).show();
                } else if (zipcode.length() < 9) {
                    Toast.makeText(this, getResources().getString(R.string.err_invalid_zip_code), Toast.LENGTH_LONG).show();
                } else {
                    // Transfer the answers to the Patient object before proceeding
                    this.patient.setCityName(cityName);
                    this.patient.setNeighborhoodName("");
                    this.patient.setOtherCity(true);
                    this.patient.setZipCode(zipcode);
                    // Call next activity
                    this.gotoNextActivity();
                }
            } else {
                // Transfer the answers to the Patient object before proceeding
                this.patient.setCityName(cityName);
                this.patient.setNeighborhoodName("");
                this.patient.setOtherCity(false);
                this.patient.setZipCode("");
                // Call next activity
                this.gotoNextActivity();
            }
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