package com.example.myimc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Activity_menu extends AppCompatActivity {
    private TextView button_calc_imc;
    private TextView button_res_imc;
    private TextView button_activite_sportive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        button_calc_imc = findViewById(R.id.menu_calcul_imc);
        button_res_imc = findViewById(R.id.menu_result_imc);
        button_activite_sportive = findViewById(R.id.menu_activite_sportive);


        button_calc_imc.setOnClickListener((v)-> {
            Intent intent = new Intent(Activity_menu.this, Activity_calcul_imc.class);
            startActivity(intent);
        });
        button_res_imc.setOnClickListener((v)-> {
            Intent intent = new Intent(Activity_menu.this, Activity_result_imc.class);
            startActivity(intent);
        });
        button_activite_sportive.setOnClickListener((v)-> {
            Intent intent = new Intent(Activity_menu.this, Activity2.class);
            startActivity(intent);
        });

    }
    public static void setButtonGoToMenu(Context ctx, View button) {
        button.setOnClickListener((v) -> {
            Intent intent = new Intent(ctx, Activity_menu.class);
//        intent.putExtra("dataSend", "send auto");
            ctx.startActivity(intent);
        });
    }
    public static void setButtonGoToAny(Context ctx, View button, Class<?> activity_target) {
        button.setOnClickListener((v) -> {
            Intent intent = new Intent(ctx, activity_target);
//        intent.putExtra("dataSend", "send auto");
            ctx.startActivity(intent);
        });
    }
}