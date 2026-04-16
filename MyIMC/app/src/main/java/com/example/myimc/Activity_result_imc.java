package com.example.myimc;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.myimc.Activity_menu.setButtonGoToMenu;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Activity_result_imc extends AppCompatActivity {
    private TextView textView_resultat;
    private Button button_redo_calc;
    private Button button_historique_imc;
    private TextView textView_info;
    private TextView[] textViews_categories;
    private int poids;
    private int taille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_imc);


        SqliteMaDataBase db = new SqliteMaDataBase(this);

        textView_resultat = findViewById(R.id.res_imc_textView_result);
        button_redo_calc = findViewById(R.id.res_imc_button_recalculer);
        button_historique_imc = findViewById(R.id.res_imc_button_historique);
        textView_info = findViewById(R.id.res_imc_textView_info);

        textViews_categories = new TextView[7];
        textViews_categories[0] = findViewById(R.id.res_imc_textView_cat0);
        textViews_categories[1] = findViewById(R.id.res_imc_textView_cat1);
        textViews_categories[2] = findViewById(R.id.res_imc_textView_cat2);
        textViews_categories[3] = findViewById(R.id.res_imc_textView_cat3);
        textViews_categories[4] = findViewById(R.id.res_imc_textView_cat4);
        textViews_categories[5] = findViewById(R.id.res_imc_textView_cat5);
        textViews_categories[6] = findViewById(R.id.res_imc_textView_cat6);

        try {
            poids  = getIntent().getExtras().getInt("poids");
            taille = getIntent().getExtras().getInt("taille");

            Log.d("result_imc", "poids: "+poids);
            Log.d("result_imc", "taille: "+taille);

            double taille_m = (double) taille / 100;
            double imc = (double) poids / (taille_m*taille_m);
            // trick to get round with 1 decimal after .
            imc = Math.round(imc*10.0) / 10.0;
            Log.d("result_imc", "imc: "+imc);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String date = sdf.format(new Date());
            Log.d("result_imc", "date ajd: "+date);

            db.insertionIMC(taille, poids, imc, date);

            textView_resultat.setText(""+imc);

            selectCategory(imc);

        } catch (Exception e) {
            init();

            Log.d("result_imc", "1");

            Cursor cursor = db.lireTableIMC();

            if (cursor == null) {
                Toast.makeText(this, "Pas d'imc stocker dans la base de donnee", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("result_imc", "2");

                StringBuffer buffer = new StringBuffer();

                cursor.moveToFirst();
                Log.d("result_imc", "3");

                Imc Imc = null;

                Log.d("result_imc", "3.5");
                while(!cursor.isAfterLast()) {
                    Log.d("result_imc", "4");
                    int taille = cursor.getInt(1);
                    int poids = cursor.getInt(2);
                    double imc = cursor.getDouble(3);
                    String date = cursor.getString(4);

                    Log.d("result_imc", "5");
                    Imc = new Imc(taille, poids, imc, date);

                    Log.d("result_imc", "6");
                    db.appendToBufferAllColumnForIMC(cursor, buffer);
                    cursor.moveToNext();
                    Log.d("result_imc", "7");
                }
                if (Imc != null) {
                    // Pour debug
//                    db.infoMaBaseIMC("res", buffer, this);

                    textView_resultat.setText(""+Imc.getIMC());
                    selectCategory(Imc.getIMC());
                }

                cursor.close();

                Log.d("result_imc", "*");
            }
        }

        setButtonGoToMenu(this, findViewById(R.id.resultat_imc_floatingActionButton));

        button_historique_imc.setOnClickListener((v) -> {
            Intent intent = new Intent(Activity_result_imc.this, Activity_historique_imc.class);
            startActivity(intent);
        });
        button_redo_calc.setOnClickListener((v) -> {
            Intent intent = new Intent(Activity_result_imc.this, Activity_calcul_imc.class);

//            intent.putExtra("poids", poids);
//            intent.putExtra("taille", taille);
//            intent.putExtra("is_recalculated", true);

//            Log.d("result_imc", "send poids: "+poids);
//            Log.d("result_imc", "send taille: "+taille);

            startActivity(intent);
        });

        db.close();
    }

    private void selectCategory(double imc) {

        if (imc > 40) {
            showCategory(0);
            textView_info.setText("IMC supérieur à 40. Cette valeur est associée à un risque fortement accru de complications cardiovasculaires, respiratoires et métaboliques.");

            int dark_red = ContextCompat.getColor(this, R.color.dark_red);
            textView_resultat.setBackgroundColor(dark_red);
        }
        if (imc <= 40 && imc > 35) {
            showCategory(1);
            textView_info.setText("IMC compris entre 35 et 40. Cette catégorie est liée à une probabilité plus élevée de complications métaboliques et articulaires.");

            int light_red = ContextCompat.getColor(this, R.color.light_red);
            textView_resultat.setBackgroundColor(light_red);
        }
        if (imc <= 35 && imc > 30) {
            showCategory(2);
            textView_info.setText("IMC compris entre 30 et 35. Cette plage est associée à un risque accru d’hypertension, de diabète de type 2 et de maladies cardiovasculaires.");

            int brown = ContextCompat.getColor(this, R.color.brown);
            textView_resultat.setBackgroundColor(brown);
        }
        if (imc <= 30 && imc > 25) {
            showCategory(3);
            textView_info.setText("IMC compris entre 25 et 30. Cette catégorie est statistiquement associée à une augmentation progressive du risque de troubles métaboliques et cardiovasculaires.");

            int light_green = ContextCompat.getColor(this, R.color.light_green);
            textView_resultat.setBackgroundColor(light_green);
        }
        if (imc <= 25 && imc > 18.5) {
            showCategory(4);
            textView_info.setText("IMC compris entre 18,5 et 25. Cette plage est généralement associée aux risques de santé les plus faibles au niveau populationnel.");

            int dark_green = ContextCompat.getColor(this, R.color.dark_green);
            textView_resultat.setBackgroundColor(dark_green);
        }
        if (imc <= 18.5 && imc > 16.5) {
            showCategory(5);
            textView_info.setText("IMC compris entre 16,5 et 18,5. Cette plage peut être associée à un risque plus élevé de carences nutritionnelles et de baisse d’énergie selon les individus.");

            int turquoise= ContextCompat.getColor(this, R.color.turquoise);
            textView_resultat.setBackgroundColor(turquoise);
        }
        if (imc <= 16.5) {
            showCategory(6);
            textView_info.setText("IMC inférieur à 16,5. Cette valeur est statistiquement associée à un risque accru de fatigue, de fragilité immunitaire et de complications liées à une insuffisance nutritionnelle.");

            int blue = ContextCompat.getColor(this, R.color.blue);
            textView_resultat.setBackgroundColor(blue);
        }
    }
    private void init() {
        textView_resultat.setBackgroundColor(Color.LTGRAY);
        textView_resultat.setText("0");
        button_redo_calc.setVisibility(INVISIBLE);
        button_historique_imc.setVisibility(INVISIBLE);

        for(TextView category: textViews_categories) {

            // width = 275dp
            var params = category.getLayoutParams();
            params.width = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    275,
                    category.getResources().getDisplayMetrics()
            );
            category.setLayoutParams(params);

            // text size = 18sp
            category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

            // padding = 8dp
            int padding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    8,
                    category.getResources().getDisplayMetrics()
            );
            category.setPadding(padding, padding, padding, padding);

            // text style normal
            category.setTypeface(null, Typeface.NORMAL);
        }
    }

    private void showCategory(int id) {
        TextView category = textViews_categories[id];

        button_redo_calc.setVisibility(VISIBLE);
        button_historique_imc.setVisibility(VISIBLE);

//        Vous etes dans la zone optimal"

        // width = match_parent
        var params = category.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        category.setLayoutParams(params);

        // text size = 20sp
        category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        // padding = 16dp
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                16,
                category.getResources().getDisplayMetrics()
        );
        category.setPadding(padding, padding, padding, padding);

        // text style bold
        category.setTypeface(null, Typeface.BOLD);
    }
}