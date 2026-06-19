package com.example.voleyv;

import android.util.Log;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MyValidation {
    private final Pattern PASSWORD_PATTERN = Pattern.compile(
"^" +
    "(?=.*[0-9])" +       // au moins 1 chiffres
    "(?=.*[a-z])" +      // au moins une minuscule
    "(?=.*[A-Z])" +      // au moins une majuscule
    "(?=.*[@#!$%^&+])" + // au moins un char special
    "(?=\\S+$)" +        // pas d'espace vide
    ".{6,20}" +         // minimum 6 char et max 20
    "$");

    public Boolean register(TextInputLayout email, TextInputLayout login, TextInputLayout password, TextInputLayout confirm_password) {
        return validEmail(email) && validLogin(login) && validPassword(password) && validConfirmPassword(confirm_password, password);
    }

    public Boolean login(TextInputLayout login, TextInputLayout password) {
        return validLogin(login) && validPassword(password);
    }

    private Boolean validEmail(TextInputLayout emailLayout) {
        var email = emailLayout.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            emailLayout.setError("Email ne doit pas etre vide");
            emailLayout.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError(email+" n'est pas un email valide");
            emailLayout.requestFocus();
            return false;
        }
        emailLayout.setError(null);
        return true;
    }
    private Boolean validLogin(TextInputLayout loginLayout) {
        var login = loginLayout.getEditText().getText().toString().trim();
        if (login.isEmpty()) {
            loginLayout.setError("Login ne doit pas etre vide");
            loginLayout.requestFocus();
            return false;
        }
        if (login.length() > 10) {
            loginLayout.setError("Login est trop long");
            return false;
        }
        loginLayout.setError(null);
        return true;
    }
    //        Pattern p = Pattern.compile("A");
//        Matcher m = p.matcher("aaab");
//        static final Pattern EMAIL_ADDRESSE = Pattern.compile("");
    private Boolean validPassword(TextInputLayout passwordLayout) {
        var password = passwordLayout.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            passwordLayout.setError("Password ne doit pas etre vide");
            passwordLayout.requestFocus();
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordLayout.setError(password +" n'est pas un password valide");
            passwordLayout.requestFocus();
            return false;
        }
        passwordLayout.setError(null);
        return true;
    }
    private Boolean validConfirmPassword(TextInputLayout confirm_passwordLayout, TextInputLayout passwordLayout) {
        var confirm_password = confirm_passwordLayout.getEditText().getText().toString().trim();
        var password = passwordLayout.getEditText().getText().toString().trim();

        if (confirm_password.isEmpty()) {
            confirm_passwordLayout.setError("Confirm password ne doit pas etre vide");
            confirm_passwordLayout.requestFocus();
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(confirm_password).matches()) {
            confirm_passwordLayout.setError(confirm_password +" n'est pas un password valide");
            confirm_passwordLayout.requestFocus();
            return false;
        }
        if (!confirm_password.equals(password)) {
            Log.d("password", "c_p: "+ confirm_password);
            Log.d("password", "p: "+ password);
            confirm_passwordLayout.setError("password et confirm password ne sont pas identique");
            return false;
        }
        confirm_passwordLayout.setError(null);
        return true;
    }


}
