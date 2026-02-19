package com.example.myimc;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        editText = findViewById(R.id.editTextText);
        textView = findViewById(R.id.textView);

        // reactive seach when pressing a key
        editText.setOnKeyListener((view, i, keyEvent) -> {
            String s = editText.getText().toString().trim();
            textView.setText(s);
            return false;
        });
        // use like to find similar words ex course, course a pied
    }
}