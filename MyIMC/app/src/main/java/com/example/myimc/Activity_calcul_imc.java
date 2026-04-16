package com.example.myimc;

import static com.example.myimc.Activity_menu.setButtonGoToMenu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_calcul_imc extends AppCompatActivity {
    private Button button_calculer;
    private EditText editText_poids;
    private EditText editText_taille;
    private int poids;
    private int taille;
    private boolean is_recalculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_imc);

        button_calculer = findViewById(R.id.calc_imc_button_calculer);
        editText_poids  = findViewById(R.id.calc_imc_editText_poids);
        editText_taille = findViewById(R.id.calc_imc_editText_taille);

        button_calculer.setOnClickListener((v) -> {
            String input_poids = editText_poids.getText().toString().trim();
            String input_taille = editText_taille.getText().toString().trim();

            if (input_taille.isEmpty()) {
                Toast.makeText(this, "mettez une taille", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                taille = Integer.parseInt(input_taille);
                Log.d("taille", "taille: "+taille);
            } catch (Exception e) {
                Log.e("taille", "convert taille string to int");
                Toast.makeText(this, "Mettez des chiffres pour la taille", Toast.LENGTH_SHORT).show();
                return;
            }
            if (taille < 100 || taille > 250) {
                Log.e("taille", "Err: taille <= 100cm ou > a 250cm");
                Toast.makeText(this, "Mettez une taille entre 100 et 250cm", Toast.LENGTH_SHORT).show();
                return;
            }

            if (input_poids.isEmpty()) {
                Toast.makeText(this, "mettez un poid", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                poids = Integer.parseInt(input_poids);
                Log.d("poids", "poids: "+poids);
            } catch (Exception e) {
                Log.e("poids", "convert poids string to int");
                Toast.makeText(this, "Mettez des chiffres pour le poids", Toast.LENGTH_SHORT).show();
                return;
            }

            if (poids < 40 || poids > 250) {
                Toast.makeText(this, "Mettez un poids entre 40 et 250kg", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Activity_calcul_imc.this, Activity_result_imc.class);

            intent.putExtra("poids", poids);
            intent.putExtra("taille", taille);

            is_recalculated = true;

            startActivity(intent);
        });

        setButtonGoToMenu(this, findViewById(R.id.calcul_imc_floatingActionButton));

//        try {
//            is_recalculated = getIntent().getExtras().getBoolean("is_recalculated");
//        } catch (Exception e) {
//            Log.d("calc_imc", "not recalculated");
//        }
//
//        if (is_recalculated) {
//            poids  = getIntent().getExtras().getInt("poids");
//            taille = getIntent().getExtras().getInt("taille");
//
//            editText_poids.setText(poids);
//            editText_taille.setText(taille);
//            Log.d("calcul_imc", "pas de valeur initial");
//        }


    }
}