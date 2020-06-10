package com.dam.kdcovid_app.control;

public final class Util {
    public static boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(String phone) {
        if (phone.length() < 13 || phone.length() > 14)
            return false;
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
