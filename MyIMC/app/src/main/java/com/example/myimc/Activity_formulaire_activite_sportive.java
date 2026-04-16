package com.example.myimc;

import static com.example.myimc.Activity_menu.setButtonGoToAny;
import static com.example.myimc.Activity_menu.setButtonGoToMenu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Activity_formulaire_activite_sportive extends AppCompatActivity {
    private Button button_ajouter;
    private EditText editText_nom_activite;
    private EditText editText_date;
    private TextView textView_date;
    private EditText editText_duree;
    private EditText editText_commentaire;

    private String input_date ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_activite_sportive);

        // TODO: check each fields, put toast for errors
        // check how to define values for padding with a var, ...

        editText_nom_activite = findViewById(R.id.formulaire_activite_sportive_editText_nom_activite);
        editText_date = findViewById(R.id.formulaire_activite_sportive_editText_date);
        editText_duree = findViewById(R.id.formulaire_activite_sportive_editText_duree);
        editText_commentaire = findViewById(R.id.formulaire_activite_sportive_editText_commentaire);

        textView_date = findViewById(R.id.formulaire_activite_sportive_textView_date);
        textView_date.setOnClickListener((v) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            input_date = sdf.format(new Date());
            editText_date.setText(input_date);
        });

        button_ajouter = findViewById(R.id.formulaire_activite_sportive_button_ajouter);
        button_ajouter.setOnClickListener((v) -> {

            String input_nom_activ_sportive  = editText_nom_activite.getText().toString().trim();
            input_date = editText_date.getText().toString().trim();
            String input_duree = editText_duree.getText().toString().trim();
            String input_commentaire = editText_commentaire.getText().toString().trim();

            if (!validNomActivSportive(input_nom_activ_sportive)
            || !validDate(input_date)
            || !validDuree(input_duree)){
                return;
            }


            try {
                int duree = Integer.parseInt(input_duree);
                SqliteMaDataBase db = new SqliteMaDataBase(this);
                db.insertionActivSportive(input_nom_activ_sportive, input_date, duree, input_commentaire);
            } catch (Exception e) {
                Toast.makeText(this, "Error de conversion", Toast.LENGTH_SHORT).show();
            }

           Intent intent = new Intent(Activity_formulaire_activite_sportive.this, Activity2.class);
           Toast.makeText(this, "Activite sportive ajouter!", Toast.LENGTH_SHORT).show();
           startActivity(intent);
        });

        setButtonGoToAny(this, findViewById(R.id.formulaire_activite_sportive_floatingActionButton_go_to_menu), Activity2.class);
    }
    private Boolean validNomActivSportive(String nom) {
        if (nom.isEmpty()) {
            Toast.makeText(this, "Remplissez le champ \"Nom de l'activite\"", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (nom.length() <= 2 ) {
            Toast.makeText(this, "Nom de l'activite trop court", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (nom.length() >= 50) {
            Toast.makeText(this, "Nom de l'activite trop long", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private Boolean validDate(String date) {
        if (date.isEmpty()) {
            Toast.makeText(this, "Remplissez le champ \"Date\"", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
            Toast.makeText(this, "Format date invalide", Toast.LENGTH_SHORT).show();
           return false;
        }
        return true;
    }
    private Boolean validDuree(String duree) {
        if (duree.isEmpty()) {
            Toast.makeText(this, "Remplissez le champ \"Duree\"", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!duree.matches("\\d+")) {
            // only numbers
            Toast.makeText(this, "Duree n'est pas un nombre", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}