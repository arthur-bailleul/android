package com.example.regex;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout textInputLayout_email;
    private TextInputLayout textInputLayout_login;
    private TextInputLayout textInputLayout_password;
    private TextInputLayout textInputLayout_confirm_password;

//    private EditText editText_email, editText_login, editText_password, editText_confirm_password;

    private String email, login, password, confirm_password;

    private Button button_valider;
    private ProgressBar progressBar;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
    "^" +
        "(?=.*[0-9])" +       // au moins 1 chiffres
        "(?=.*[a-z])" +      // au moins une minuscule
        "(?=.*[A-Z])" +      // au moins une majuscule
        "(?=.*[@#!$%^&+])" + // au moins un char special
        "(?=\\S+$)" +        // pas d'espace vide
        ".{6,20}" +         // minimum 6 char et max 20
        "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textInputLayout_email = findViewById(R.id.input_email);
        textInputLayout_login = findViewById(R.id.input_login);
        textInputLayout_password = findViewById(R.id.input_password);
        textInputLayout_confirm_password = findViewById(R.id.input_confirm_password);

//        editText_email = textInputLayout_email.getEditText();
//        editText_login = textInputLayout_password.getEditText();
//        editText_password = textInputLayout_password.getEditText();
//        editText_confirm_password = textInputLayout_confirm_password.getEditText();

        button_valider = findViewById(R.id.button_valider);
        progressBar = findViewById(R.id.progressBar);

        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                validLogin();
//                validEmail();
//                validPassword();
//                validConfirmPassword();

//                if (!validEmail() || !validLogin() || !validPassword() || !validConfirmPassword())
                if (!valid())
                    return;
                progressBar.setVisibility(View.VISIBLE);
                button_valider.setVisibility(View.GONE);

                reset();
            }
        });

    }

    public Boolean valid() {
        return validEmail() && validLogin() && validPassword() && validConfirmPassword();
    }
    public Boolean validEmail() {
        email = textInputLayout_email.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            textInputLayout_email.setError("Email ne doit pas etre vide");
            textInputLayout_email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayout_email.setError(email+" n'est pas un email valide");
            textInputLayout_email.requestFocus();
            return false;
        }
        textInputLayout_email.setError(null);
        return true;
    }
    public Boolean validLogin() {
        login = textInputLayout_login.getEditText().getText().toString().trim();
        if (login.isEmpty()) {
            textInputLayout_login.setError("Login ne doit pas etre vide");
            textInputLayout_login.requestFocus();
            return false;
        }
        if (login.length() > 10) {
            textInputLayout_login.setError("Login est trop long");
            return false;
        }
        textInputLayout_login.setError(null);
        return true;
    }
//        Pattern p = Pattern.compile("A");
//        Matcher m = p.matcher("aaab");
//        static final Pattern EMAIL_ADDRESSE = Pattern.compile("");
    public Boolean validPassword() {
        password = textInputLayout_password.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputLayout_password.setError("Password ne doit pas etre vide");
            textInputLayout_password.requestFocus();
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            textInputLayout_password.setError(password +" n'est pas un password valide");
            textInputLayout_password.requestFocus();
            return false;
        }
        textInputLayout_password.setError(null);
        return true;
    }
    public Boolean validConfirmPassword() {
        confirm_password = textInputLayout_confirm_password.getEditText().getText().toString().trim();
        if (confirm_password.isEmpty()) {
            textInputLayout_confirm_password.setError("Confirm password ne doit pas etre vide");
            textInputLayout_confirm_password.requestFocus();
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(confirm_password).matches()) {
            textInputLayout_confirm_password.setError(confirm_password +" n'est pas un password valide");
            textInputLayout_confirm_password.requestFocus();
            return false;
        }
        if (!confirm_password.equals(password)) {
            Log.d("password", "c_p: "+ confirm_password);
            Log.d("password", "p: "+ password);
            textInputLayout_confirm_password.setError("password et confirm password ne sont pas identique");
            return false;
        }
        textInputLayout_confirm_password.setError(null);
        return true;
    }

    public void reset() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                button_valider.setVisibility(View.VISIBLE);
            }
        }, 4_000);
    }
}