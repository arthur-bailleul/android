package com.example.voleyv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    private TextInputLayout textInputLayout_email;
    private TextInputLayout textInputLayout_login;
    private TextInputLayout textInputLayout_password;
    private TextInputLayout textInputLayout_confirm_password;

    private Button button_register;
    private ProgressBar progressBar;

    private RequestQueue queue;
    private MyRequest request;

    private MyValidation validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputLayout_email = findViewById(R.id.textInputLayout_register_email);
        textInputLayout_login = findViewById(R.id.textInputLayout_register_login);
        textInputLayout_password = findViewById(R.id.textInputLayout_register_password);
        textInputLayout_confirm_password = findViewById(R.id.textInputLayout_register_confirm_password);

        button_register = findViewById(R.id.button_register_register);
        progressBar = findViewById(R.id.progressBar_register);

        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);


        validator = new MyValidation();

        progressBar.setVisibility(View.GONE);
        button_register.setVisibility(View.VISIBLE);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = textInputLayout_email.getEditText().getText().toString().trim();
                final String login = textInputLayout_login.getEditText().getText().toString().trim();
                final String password = textInputLayout_password.getEditText().getText().toString().trim();
                final String confirm_password = textInputLayout_confirm_password.getEditText().getText().toString().trim();

                Log.d("Register", "email: "+ email);
                Log.d("Register", "login: "+ login);
                Log.d("Register", "password: "+ password);
                Log.d("Register", "confirm_password: "+ confirm_password);


                if (!validator.register(textInputLayout_email, textInputLayout_login, textInputLayout_password, textInputLayout_confirm_password))
                    return;

                progressBar.setVisibility(View.VISIBLE);
                button_register.setVisibility(View.GONE);

                request.register(email, login, password, confirm_password, new MyRequest.RetoursPHP() {
                    @Override
                    public void toutOK(String message) {
                        Log.d("PHP", "messagePHP: " + message);
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }

                    @Override
                    public void pasOK(String message) {
                        Log.d("PHP", "passage dans PAS OK de register Activity");
                        Toast.makeText(Register.this, "Attention: "+ message, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void systemError(String message) {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

                reset();
            }
        });

    }


    public void reset() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                button_register.setVisibility(View.VISIBLE);
            }
        }, 4_000);
    }
}