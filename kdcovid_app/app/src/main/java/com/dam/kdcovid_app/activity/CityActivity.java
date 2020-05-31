package com.dam.kdcovid_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.content.Intent;

import com.dam.kdcovid_app.R;
import com.dam.kdcovid_app.model.Patient;

public class CityActivity extends AppCompatActivity {

    private Patient patient;
    private RadioButton rdbSalinas;
    private RadioButton rdbAracuai;
    private RadioButton rdbTaiobeiras;
    private RadioButton rdbCoronelMurta;
    private RadioButton rdbSaoJoaoDoParaiso;
    private RadioButton rdbJanauba;
    private RadioButton rdbPorteirinha;
    private RadioButton rdbMontesClaros;
    private RadioButton rdbOtherCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        // Set up visual components
        this.setUpViewById();
        // Get Patient object
        Bundle bundle = getIntent().getExtras();
        this.patient = (Patient)bundle.get("patient");
    }

    private void setUpViewById() {
        rdbSalinas = findViewById(R.id.rdbSalinas);
        rdbAracuai = findViewById(R.id.rdbAracuai);
        rdbTaiobeiras = findViewById(R.id.rdbTaiobeiras);
        rdbCoronelMurta = findViewById(R.id.rdbCoronelMurta);
        rdbSaoJoaoDoParaiso = findViewById(R.id.rdbSaoJoaoDoParaiso);
        rdbJanauba = findViewById(R.id.rdbJanauba);
        rdbPorteirinha = findViewById(R.id.rdbPorteirinha);
        rdbMontesClaros = findViewById(R.id.rdbMontesClaros);
        rdbOtherCity = findViewById(R.id.rdbOtherCity);
    }

    public void onclickBtnCityNext(View view) {
        // Transfer the answers to the Patient object before proceeding
        this.patient.setCitySalinas(rdbSalinas.isChecked());
        this.patient.setCityAracuai(rdbAracuai.isChecked());
        this.patient.setCityTaiobeiras(rdbTaiobeiras.isChecked());
        this.patient.setCityCoronelMurta(rdbCoronelMurta.isChecked());
        this.patient.setCitySaoJoaoDoParaiso(rdbSaoJoaoDoParaiso.isChecked());
        this.patient.setCityJanauba(rdbJanauba.isChecked());
        this.patient.setCityPorteirinha(rdbPorteirinha.isChecked());
        this.patient.setCityMontesClaros(rdbMontesClaros.isChecked());
        this.patient.setOtherCity(rdbOtherCity.isChecked());
        // Call next activity
        this.gotoNextActivity();
    }

    private void gotoNextActivity() {
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), NeighborhoodActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
        //Toast.makeText(CityActivity.this, "Ainda não fiz essa parte!", Toast.LENGTH_LONG).show();
    }

}
