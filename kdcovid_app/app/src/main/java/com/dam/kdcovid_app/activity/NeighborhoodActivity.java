package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class NeighborhoodActivity extends AppCompatActivity {

    private Patient patient;
    private Spinner spnNeighborhood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood);
        // Set up visual components
        this.setUpViewById();
        // Get Patient object
        //Bundle bundle = getIntent().getExtras();
        //this.patient = (Patient) bundle.get("patient");
    }

    private void setUpViewById() {
        spnNeighborhood = findViewById(R.id.spnNeighborhood);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, R.array.neighborhood_salinas);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.neighborhood_salinas, R.layout.spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNeighborhood.setAdapter(adapter);
    }
}