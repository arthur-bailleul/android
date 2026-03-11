package com.example.myimc;

import static com.example.myimc.Activity_menu.setButtonGoToMenu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Activity2 extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private FloatingActionButton floatingActionButton_add_activity;
    private ArrayList<ActivSportive> listSport;
    private ArrayList<ActivSportive>  listSportFiltered;

    private ArrayAdapter<ActivSportive> arrayAdapter;
    private ArrayAdapter<ActivSportive> arrayAdapterFiltered;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        // GET XML
        editText = findViewById(R.id.editTextText);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.activites_sportives_listView);
        floatingActionButton_add_activity = findViewById(R.id.activite_sportive_floatingActionButton_add_activity);
        Log.d("debug", "1");

        listSport = new ArrayList<ActivSportive>(getActivitesFromDB());

        Log.d("debug", "2");
        listSportFiltered = new ArrayList<ActivSportive>();

        Log.d("debug", "3");
//        listSport.add("Escalade");
//        listSport.add("Running");
//        listSport.add("Natation");
//        listSport.add("Escalade");

        Log.d("debug", "4");

        // array adapter
        arrayAdapter = new ArrayAdapter<>(this, R.layout.activite_sportive, listSport);
        arrayAdapterFiltered = new ArrayAdapter<>(Activity2.this, R.layout.activite_sportive, listSportFiltered);

        Log.d("debug", "5");

        listView.setAdapter(arrayAdapter);

        Log.d("debug", "6");
        // builder pattern
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ActivSportive activSportive = listSport.get(Math.toIntExact(id));
            new AlertDialog.Builder(this)
            .setTitle(activSportive.getNom())
            .setPositiveButton("OK", null)
            .setMessage(activSportive.getInfo())
            .show();
        });

        interactiveInputSearch(editText, textView);

        setButtonGoToMenu(this, findViewById(R.id.activite_sportive_go_to_menu));
        floatingActionButton_add_activity.setOnClickListener((v) -> {
            Intent intent = new Intent(Activity2.this, Activity_formulaire_activite_sportive.class);
            startActivity(intent);
        });
    }

    private void interactiveInputSearch(EditText editText, TextView textView) {
        // dont put do too much sql request, stock recent search in a list or something to load at start

        // reactive seach when pressing a key
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = editText.getText().toString().trim().toLowerCase();
                if (input.isEmpty()) {
//                    textView.setText("");
                    listView.setAdapter(arrayAdapter);
                    return;
                }
//                textView.setText(input);
//                        listSport.add(input);

                listSportFiltered = listSport.stream()
                        .filter(sport -> sport.toString().toLowerCase().contains(input))
                        .collect(Collectors.toCollection(ArrayList::new));

                arrayAdapterFiltered = new ArrayAdapter<>(Activity2.this, R.layout.activite_sportive, listSportFiltered);
                listView.setAdapter(arrayAdapterFiltered);
            }
        });
    }
    public ArrayList<ActivSportive> getActivitesFromDB() {
        Log.d("debug", "5");
        var activSportives = new ArrayList<ActivSportive>();

        Log.d("debug", "6");
        SqliteMaDataBase db = new SqliteMaDataBase(this);
        Log.d("debug", "7");
        StringBuffer buffer = new StringBuffer();
        Log.d("debug", "8");

//        db.insertionActivSportive("test", "2025-12-01", 26, "commentaire");
//        db.insertionIMC(170, 60, 22.5, "2026-01-01");

        Log.d("debug", "9");
        Cursor cursor = db.lireTableActivSportive();

        Log.d("debug", "10");
        cursor.moveToFirst();

        Log.d("debug", "11");
        while(!cursor.isAfterLast()) {
            String nom = cursor.getString(1);
            String date = cursor.getString(2);
            int duree = cursor.getInt(3);
            String commentaire = cursor.getString(4);

            ActivSportive activSportive = new ActivSportive(nom, date, duree, commentaire);
            activSportives.add(activSportive);

            db.appendToBufferAllColumnForActivSportive(cursor, buffer);
            cursor.moveToNext();
        }
        // POUR DEBUG
//        db.infoMaBaseActivSportive("res", buffer, this);

        cursor.close();
        db.close();
        return activSportives;
    }
}


