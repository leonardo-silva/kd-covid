package com.ifnmg.dmelab.kdcovid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.ifnmg.dmelab.kdcovid.R;
import com.ifnmg.dmelab.kdcovid.control.Covid19;
import com.ifnmg.dmelab.kdcovid.control.api.Api;
import com.ifnmg.dmelab.kdcovid.control.api.MySingleton;
import com.ifnmg.dmelab.kdcovid.model.Patient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.ifnmg.dmelab.kdcovid.control.Util.isValidMail;
import static com.ifnmg.dmelab.kdcovid.control.Util.isValidMobile;

public class PhoneActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;
    private static final int INTERVAL_DAYS = 2;

    //private static boolean error_db;
    //private static boolean patient_already_recorded;
    //private static boolean patient_validation_ok;
    //private static String http_get_response;

    private Patient patient;
    private ProgressBar pgbDB;
    //private TextView tvHeaderPhone;
    //private TextView tvHeaderFeelOkPhone;
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
        // Configure the progress bar to shutdown when http requests finish
        MySingleton.getInstance(this).getRequestQueue().addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
            @Override
            public void onRequestFinished(Request<String> request) {
                pgbDB.setVisibility(View.GONE);
            }
        });
    }

    private void setUpViewById() {
        pgbDB = findViewById(R.id.pgbDB);
        etEnterEmail = findViewById(R.id.etEnterEmail);
        etEnterPhone = findViewById(R.id.etEnterPhone);

        // Brazilian cell phone mask
        SimpleMaskFormatter smf =
                new SimpleMaskFormatter(getResources().getString(R.string.cell_phone_mask));
        MaskTextWatcher mtw = new MaskTextWatcher(etEnterPhone, smf);
        etEnterPhone.addTextChangedListener(mtw);

        //tvHeaderPhone = findViewById(R.id.tvHeaderPhone);
        //tvHeaderFeelOkPhone = findViewById(R.id.tvHeaderFeelOkPhone);
        // Control header
        //if (! this.patient.getHasSymptom()) {
        //    tvHeaderPhone.setVisibility(View.GONE);
        //    tvHeaderFeelOkPhone.setVisibility(View.VISIBLE);
        //}
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
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            // Transfer the answers to the Patient object before proceeding
            this.patient.setEmail(etEnterEmail.getText().toString());
            this.patient.setPhone(etEnterPhone.getText().toString());
            this.patient.setAndroid_id(androidId);
            this.patient.setResultCode((byte)Covid19.Screening(this.patient.generateCovid19Input()).getValue());
            // Verify if the questionnaire was already answered in the last INTERVAL_DAYS
            this.verifyPatientAlreadyRecorded(INTERVAL_DAYS);
        }
    }

    private void verifyPatientAlreadyRecorded(int intervalDays) {
        String url;
        url = Api.URL_READ_PATIENTS.replace("1?", etEnterEmail.getText().toString());
        url = url.replace("2?", etEnterPhone.getText().toString());
        url = url.replace("3?", ""+intervalDays);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_execute_db_operation), Toast.LENGTH_LONG).show();
                            } else {
                                if (response.getJSONArray("patients").length() > 0) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_db_record_exists), Toast.LENGTH_LONG).show();
                                } else {
                                    recordPatientToDB();
                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_generate_json), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_execute_db_operation), Toast.LENGTH_LONG).show();
                }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        pgbDB.setVisibility(View.VISIBLE);
    }

    protected void recordPatientToDB() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, Api.URL_CREATE_PATIENT,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_execute_db_operation), Toast.LENGTH_LONG).show();
                            } else {
                                // Call next activity
                                gotoNextActivity();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_execute_db_operation), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_execute_db_operation), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                String patient_json = null;
                try {
                    patient_json = patient.objToJson();
                } catch (JsonProcessingException e) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.err_generate_json), Toast.LENGTH_LONG).show();
                }
                Map<String, String>  params = new HashMap<>();
                params.put("patient_json", patient_json);

                return params;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(postRequest);
        pgbDB.setVisibility(View.VISIBLE);
    }
    
    private void gotoNextActivity() {
        // Call next activity
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}
