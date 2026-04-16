package com.example.java_natif;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//    private TextView textView_info_perso;
//    private TextView textView_info_perso_defaut;
//    private TextView textView_info_perso_magicien;
    private TextView textView_info_perso, textView_info_perso_defaut, textView_info_perso_magicien;

    private Personnage perso1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_info_perso = findViewById(R.id.info_perso);
        textView_info_perso_defaut = findViewById(R.id.info_perso_defaut);
        textView_info_perso_magicien = findViewById(R.id.info_perso_magicien);

        // 1: static
//        String stats = "Nom: " + Personnage.nom + "\n" +
//                "Pt de vie : " + Personnage.vita + "\n" +
//                "Pt de force : " + Personnage.force + "\n" +
//                "Pt de mana : " + Personnage.mana + "\n";

        // 2: constructor + public
//        var perso1 = new Personnage();

        // 3: constructor with params
        perso1 = new Personnage("toto", 100, 87, 76);

//        String stats = "Nom: " + perso1.nom + "\n" +
//                "Pt de vie : " + perso1.vita + "\n" +
//                "Pt de force : " + perso1.force + "\n" +
//                "Pt de mana : " + perso1.mana + "\n";

        String stats = "Nom: " + perso1.getNom() + "\n" +
                "Pt de vie : " + perso1.getVita() + "\n" +
                "Pt de force : " + perso1.getForce() + "\n" +
                "Pt de mana : " + perso1.getMana() + "\n";

        // 4: show defaults
        var perso2 = new Personnage("Tata");

        perso2.pertePv();
        perso2.pertePv(2);
        perso2.pertePv(58);

        perso2.setVita(perso2.getVita()+100);

//        perso2.vita += 100;
//        perso2.vita += 150;
//
//        String stats2 = "Nom: " + perso2.nom + "\n" +
//                "Pt de vie : " + perso2.vita + "\n" +
//                "Pt de force : " + perso2.force + "\n" +
//                "Pt de mana : " + perso2.mana + "\n";

//        perso2.setVita(100);
//        perso2.setVita(150);

        String stats2 = "Nom: " + perso2.getNom() + "\n" +
                "Pt de vie : " + perso2.getVita() + "\n" +
                "Pt de force : " + perso2.getForce() + "\n" +
                "Pt de mana : " + perso2.getMana() + "\n";

        // 5: Extends
        var magicien = new Magicien("mago", 100, 10, 200);
//        magicien.
        String stats3 = magicien.toString();

        ///  6: interface

//        augmenteForce(300);
//        changeNom("guerrier interface");
//        changeCouleur(Color.MAGENTA);

        // 7: mega guerrier

//        perso1.pertePv(this);
        perso1.pertePv(new Personnage.MegaStats() {
            @Override
            public void augmenteForce(int f) {
                perso1.setForce(f);
            }

            @Override
            public void augmenteVie(int v) {
                perso1.setVita(v);
            }

            @Override
            public void augmenteMana(int m) {
                perso1.setMana(m);
            }

            @Override
            public void changeNom(String s) {
                perso1.setNom(s);
            }

            @Override
            public void changeCouleur(int c) {
                textView_info_perso.setTextColor(c);
            }
        });

        textView_info_perso.setText(stats);
        textView_info_perso_defaut.setText(stats2);
        textView_info_perso_magicien.setText(stats3);
        Toast.makeText(this, stats, Toast.LENGTH_SHORT).show();
    }

}