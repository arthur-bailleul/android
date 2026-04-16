package com.example.fragments;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

public class MainActivity extends AppCompatActivity {
    private FragmentContainerView fragmentA;
    private FragmentContainerView fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        fragmentA = findViewById(R.id.fragmentContainerView_a);
        fragmentA = findViewById(R.id.fragmentContainerView_b);


        setContentView(R.layout.activity_main);
    }
}