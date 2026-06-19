package com.example.voleyv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    private TextInputLayout textInputLayout_login;
    private TextInputLayout textInputLayout_password;

    private Button button_register;
    private Button button_login;

    private ProgressBar progressBar;

    private RequestQueue queue;
    private MyRequest request;

    private SessionManager sessionManager;
    private MyValidation validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textInputLayout_login = findViewById(R.id.textInputLayout_login_login);
        textInputLayout_password = findViewById(R.id.textInputLayout_login_password);

        button_login = findViewById(R.id.button_login_login);
        button_register = findViewById(R.id.button_login_register);

        progressBar = findViewById(R.id.progressBar_login);

        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        sessionManager = new SessionManager(this);
        validator = new MyValidation();

        progressBar.setVisibility(View.GONE);
        button_register.setVisibility(View.VISIBLE);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String login = textInputLayout_login.getEditText().getText().toString().trim();
                final String password = textInputLayout_password.getEditText().getText().toString().trim();

                Log.d("Register", "login: "+ login);
                Log.d("Register", "password: "+ password);


                if(!validator.login(textInputLayout_login, textInputLayout_password))
                    return;

                progressBar.setVisibility(View.VISIBLE);
                button_register.setVisibility(View.GONE);

                request.login(login, password, new MyRequest.LoginCallBack() {
                    @Override
                    public void onSuccess(HashMap<String, String> map, String message) {
                        Log.d("PHP", "messagePHP: " + message);
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();

                        var intent = new Intent(Login.this, Connected.class);

                        var get_id = map.get("id");
                        var get_login = map.get("login");
                        var get_email = map.get("email");

                        Log.d("PHP", get_id);
                        Log.d("PHP", get_login);
                        Log.d("PHP", get_email);

                        intent.putExtra("login", get_login);
                        intent.putExtra("id", get_id);
                        intent.putExtra("email", get_email);

                        sessionManager.insertUser(get_id, get_login, get_email);

                        startActivity(intent);

                    }

                    @Override
                    public void onError(String message) {
                        Log.d("PHP", "passage dans PAS OK de login Activity");
                        Toast.makeText(Login.this, "Attention: "+ message, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void systemError(String message) {
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

//                request.login(login, password, new MyRequest.RetoursPHP() {
//                    @Override
//                    public void toutOK(String message) {
//                        Log.d("PHP", "messagePHP: " + message);
//                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
//
//                        var intent = new Intent(Login.this, Connected.class);
//
//                        Log.d("PHP", message);
//
//                        String[] data = message.split(";");
//
//                        var get_id = data[0];
//                        var get_login = data[1];
//                        var get_email = data[2];
//
//                        Log.d("PHP", get_id);
//                        Log.d("PHP", get_login);
//                        Log.d("PHP", get_email);
//
//                        intent.putExtra("login", get_login);
//                        intent.putExtra("id", get_id);
//                        intent.putExtra("email", get_email);
//
//
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void pasOK(String message) {
//                        Log.d("PHP", "passage dans PAS OK de login Activity");
//                        Toast.makeText(Login.this, "Attention: "+ message, Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    @Override
//                    public void systemError(String message) {
//                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
//                    }
//                });
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
                button_login.setVisibility(View.VISIBLE);
            }
        }, 4_000);
    }
}