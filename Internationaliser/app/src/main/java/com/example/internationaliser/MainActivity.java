package com.example.internationaliser;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn_switch_lang;

    private TextView titre, text1,text2;
    private String[] langs = {"en", "fr"};
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btn_switch_lang = findViewById(R.id.button_switch_lang);

        btn_switch_lang.setOnClickListener(v -> {
            if (current > langs.length-1) {
                current = 0;
                changeLang(langs[current]);
            } else {
                current++;
                changeLang(langs[current]);
            }
        });
    }

    private void init() {
        Log.d("init", "init");
//        titre.setText("");
    }

    private void changeLang(String lang) {
        titre.setText(R.string.Titre1);
        text1.setText(R.string.text1);
        text2.setText(R.string.text2);
        btn_switch_lang.setText(R.string.btn_switch_lang);
    }

}
