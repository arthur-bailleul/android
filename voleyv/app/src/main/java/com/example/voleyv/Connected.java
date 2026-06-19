package com.example.voleyv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;

public class Connected extends AppCompatActivity {
    private TextView textView_id;
    private TextView textView_login;
    private TextView textView_email;

    private Button button_new_password;
    private Button button_logout;

    private RequestQueue queue;
    private MyRequest request;

    private String id;
    private String login;
    private String email;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

        textView_id = findViewById(R.id.textView_connected_id);
        textView_login = findViewById(R.id.textView_connected_login);
        textView_email = findViewById(R.id.textView_connected_email);

        button_new_password = findViewById(R.id.button_connected_new_password);
        button_logout = findViewById(R.id.button_connected_logout);

        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);


        sessionManager = new SessionManager(this);

        if (!sessionManager.isLogged()) {
            var i = new Intent(Connected.this, Login.class);
            startActivity(i);
        }

        textView_id.setText(sessionManager.getId());
        textView_login.setText(sessionManager.getLogin());
        textView_email.setText(sessionManager.getEmail());

//        try {
//            id = getIntent().getExtras().getString("id");
//            login  = getIntent().getExtras().getString("login");
//            email = getIntent().getExtras().getString("email");
//
//            textView_id.setText(id);
//            textView_login.setText(login);
//            textView_email.setText(email);
//
//            Log.d("PHP", id);
//            Log.d("PHP", login);
//            Log.d("PHP", email);
//
//        } catch (Exception e) {
//            Toast.makeText(this, "Error get extra", Toast.LENGTH_SHORT).show();
//        }

        button_new_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Connected.this, NewPassword.class);
                startActivity(intent);
            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                var intent = new Intent(Connected.this, Login.class);
                startActivity(intent);
            }
        });

//        request.info(login, password, new MyRequest.RetoursPHP() {
//            @Override
//            public void systemError(String message) {
//
//            }
//
//            @Override
//            public void pasOK(String message) {
//
//            }
//
//            @Override
//            public void toutOK(String message) {
//
//            }
//        });
    }
}