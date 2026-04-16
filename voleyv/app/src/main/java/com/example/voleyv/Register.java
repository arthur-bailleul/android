package com.example.voleyv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;

public class Register extends AppCompatActivity {
    private EditText editText_email;
    private EditText editText_login;
    private EditText editText_password;
    private EditText editText_confirm_password;

    private Button button_register;

    private RequestQueue queue;
    private MyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_email = findViewById(R.id.editText_register_email);
        editText_login = findViewById(R.id.editText_register_login);
        editText_password = findViewById(R.id.editText_register_password);
        editText_confirm_password = findViewById(R.id.editText_register_confirm_password);

        button_register = findViewById(R.id.button_register_register);

        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = editText_email.getText().toString().trim();
                final String login = editText_login.getText().toString().trim();
                final String password = editText_password.getText().toString().trim();
                final String confirm_password = editText_confirm_password.getText().toString().trim();

                Log.d("Register", "email: "+ email);
                Log.d("Register", "login: "+ login);
                Log.d("Register", "password: "+ password);
                Log.d("Register", "confirm_password: "+ confirm_password);

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

            }
        });

    }
}