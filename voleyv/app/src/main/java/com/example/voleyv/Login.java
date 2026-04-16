package com.example.voleyv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;

public class Login extends AppCompatActivity {
    private EditText editText_login;
    private EditText editText_password;

    private Button button_register;
    private Button button_login;

    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editText_login = findViewById(R.id.editText_login_login);
        editText_password = findViewById(R.id.editText_login_password);

        button_login = findViewById(R.id.button_login_login);
        button_register = findViewById(R.id.button_login_register);

        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

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
                final String login = editText_login.getText().toString().trim();
                final String password = editText_password.getText().toString().trim();

                Log.d("Register", "login: "+ login);
                Log.d("Register", "password: "+ password);

                request.login(login, password, new MyRequest.RetoursPHP() {
                    @Override
                    public void toutOK(String message) {
                        Log.d("PHP", "messagePHP: " + message);
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();

                        var intent = new Intent(Login.this, Connected.class);

                        Log.d("PHP", message);

                        String[] data = message.split(";");

                        var get_id = data[0];
                        var get_login = data[1];
                        var get_email = data[2];

                        Log.d("PHP", get_id);
                        Log.d("PHP", get_login);
                        Log.d("PHP", get_email);

                        intent.putExtra("login", get_login);
                        intent.putExtra("id", get_id);
                        intent.putExtra("email", get_email);


                        startActivity(intent);
                    }

                    @Override
                    public void pasOK(String message) {
                        Log.d("PHP", "passage dans PAS OK de login Activity");
                        Toast.makeText(Login.this, "Attention: "+ message, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void systemError(String message) {
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}