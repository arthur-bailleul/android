package com.example.intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity3 extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // different way to do it
//        String dataSend = savedInstanceState.get("dataSend").toString();
        String dataSend = getIntent().getExtras().getString("dataSend");
//        String dataSend = getIntent().getStringExtra("dataSend");
//        String dataSend = this.getIntent().getStringExtra("dataSend");
//        String dataSend = getIntent().getSerializableExtra("dataSend").toString();
        Log.i("dataSend", "datasend: "+dataSend);

        textView = findViewById(R.id.textView);

        textView.setText(dataSend);
    }
}