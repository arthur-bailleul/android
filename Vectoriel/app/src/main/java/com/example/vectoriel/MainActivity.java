package com.example.vectoriel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView_add;
    private ImageView imageView_handi;
    private ImageView imageView_logo;
    private ImageView imageView_close;

    private LinearLayout linear_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        imageView_handi = findViewById(R.id.imageView_handi);
        linear_layout = findViewById(R.id.linear_layout);

        imageView_handi.setOnClickListener(v -> {
            Toast.makeText(this, "CLICK", Toast.LENGTH_SHORT).show();
        });
    }
}