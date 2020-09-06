package com.ifnmg.dmelab.kdcovid.activity;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
//import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifnmg.dmelab.kdcovid.R;
import com.ifnmg.dmelab.kdcovid.model.Patient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AgreementActivity extends AppCompatActivity {

    private static int requestLocationAttempts = 0;
    private static final int MAX_REQUEST_ATTEMPTS = 2;
    private static final int REQUEST_LOCATION_CODE = 1;

    private Patient patient;
    private TextView tvAgreement;
    private Button btnIAgree;
    private FusedLocationProviderClient client;
    private String gpsLocation="";

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

    @Override
    protected void onResume() {
        super.onResume();
        requestLocationAttempts = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestLocationAttempts = 0;
    }

    protected void nextActivity() {
        // Create instance of Patient
        patient = new Patient();
        patient.setVisitedPoints(gpsLocation);
        // Call AgreementActivity activity
        Intent intent = new Intent(getApplicationContext(), FeelingActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }

    private void checkGPSLocation() {
        if (requestLocationAttempts < MAX_REQUEST_ATTEMPTS) {
            // Request permission to use GPS info
            requestPermission();
        } else {
            // Go to next activity even if permission to use GPS is not granted (location = "")
            nextActivity();
        }

        /*
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            getGPSLocation();
            // Go to next activity even if GPS on mobile is off
            nextActivity();
        } else {
            if (requestLocationAttempts >= MAX_REQUEST_ATTEMPTS) {
                // Go to next activity even if permission to use GPS is not granted (location = "")
                nextActivity();
            }
        }
        */
    }

    private void requestPermission(){
        requestLocationAttempts++;
        ActivityCompat.requestPermissions(this,
                new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
    }

    private void getGPSLocation() {
        client = LocationServices.getFusedLocationProviderClient(this);

        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    gpsLocation = location.getLatitude() + " " + location.getLongitude();
                } else {
                    // Location not found - the app can go on with gpsLocation == ""
                }
                // Go to next activity even if GPS on mobile is off or permission is not granted
                nextActivity();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults
    ) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    getGPSLocation();
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.

                    // Go to next activity even if GPS on mobile is off or permission is not granted
                    nextActivity();
                }
        //}
        // Other 'case' lines to check for other
        // permissions this app might request.
        }
    }

}
