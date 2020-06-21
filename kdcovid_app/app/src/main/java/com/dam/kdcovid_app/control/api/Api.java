package com.dam.kdcovid_app.control.api;

public class Api {

    //private static final String ROOT_URL = "http://10.0.1.151/KdcovidApi/v1/Api.php?apicall=";
    private static final String ROOT_URL = "http://161.35.111.220/v1/Api.php?apicall=";

    public static final String URL_CREATE_PATIENT = ROOT_URL + "createpatient";
    public static final String URL_READ_PATIENTS = ROOT_URL + "getpatients&email=1?&phone=2?&interval_in_days=3?";

}
