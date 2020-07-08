package com.ifnmg.dmelab.kdcovid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ifnmg.dmelab.kdcovid.R;
import com.ifnmg.dmelab.kdcovid.model.Patient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AgreementActivity extends AppCompatActivity {

    private Patient patient;
    private TextView tvAgreement;
    private Button btnIAgree;
    private FusedLocationProviderClient client;
    private String gpsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        // Set up visual components
        btnIAgree = findViewById(R.id.btnIAgree);
        btnIAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get GPS Patient Location
                checkGPSLocation();
            }
        });
        tvAgreement = findViewById(R.id.tvAgreement);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
    }

    protected void nextActivity() {
        // Create instance of Patient
        this.patient = new Patient();
        this.patient.setVisitedPoints(this.gpsLocation);
        // Call AgreementActivity activity
        Intent intent = new Intent(getApplicationContext(), FeelingActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    private void checkGPSLocation() {
        // Request permission to use GPS info
        requestPermission();

        gpsLocation = "";
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            client = LocationServices.getFusedLocationProviderClient(this);

            client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        gpsLocation = location.getLatitude() + " " + location.getLongitude();
                    }
                    // Go to next activity even if GPS on mobile is off
                    nextActivity();
                }
            });
        } else {
            // Go to next activity even if permission to use GPS is not granted (location = "")
            nextActivity();
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
