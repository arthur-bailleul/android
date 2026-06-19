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

public class NewPassword extends AppCompatActivity {
    private EditText editText_old_password;
    private EditText editText_new_password;
    private EditText editText_confirm_new_password;

    private Button button_change_password;

    private SessionManager sessionManager;

    private MyRequest request;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);


        editText_old_password = findViewById(R.id.editText_new_password_old_password);
        editText_new_password = findViewById(R.id.editText_new_password_new_password);
        editText_confirm_new_password = findViewById(R.id.editText_new_password_confirm_new_password);

        button_change_password = findViewById(R.id.button_new_password_new_password);

        sessionManager = new SessionManager(this);
        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        button_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String login = sessionManager.getLogin();
                final String old_password = editText_old_password.getText().toString().trim();
                final String new_password = editText_new_password.getText().toString().trim();
                final String confirm_new_password = editText_confirm_new_password.getText().toString().trim();

                Log.d("PHP", "login: "+login);
                Log.d("PHP", "old password: "+old_password);
                Log.d("PHP", "new_password: "+new_password);
                Log.d("PHP", "confirm_new_password: "+confirm_new_password);

                request.newPassword(login, old_password, new_password, confirm_new_password, new MyRequest.NewPasswordCallBack() {

                    @Override
                    public void onSuccess(String message) {

                        Log.d("PHP", "messagePHP: " + message);
                        Toast.makeText(NewPassword.this, message, Toast.LENGTH_SHORT).show();

                        var intent = new Intent(NewPassword.this, Connected.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Log.d("PHP", "passage dans onError de New password Activity");
                        Toast.makeText(NewPassword.this, "Attention: "+ message, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void systemError(String message) {
                        Toast.makeText(NewPassword.this, message, Toast.LENGTH_SHORT).show();
                        Log.d("PHP", "SYS: "+message);
                    }
                });

            }
        });
    }
}