package com.example.sqlite;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private StringBuffer buffer;
    private String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText editText = findViewById(R.id.editText_input);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener((v) -> {
            String input = editText.getText().toString().trim().toLowerCase();

            SqliteMaDataBase db = new SqliteMaDataBase(this);
            Cursor cursor;

//            db.fillDatabase();


//            String val;
//            String key;
//            String[] keyVal = input.split(":");
//            Log.d("Data", "k: "+keyVal)
//            switch (input.substring(0,5).trim()) {
//                case "id":
//                case "age": break;
//                case "nom":
//                case "prenom":
//                default:
//                    queryDb(db,db.lireTable());
//            }


            String value;
            String key;

            if(input.startsWith("age")) {
                value = input.substring(3).trim().substring(1).trim();
                key = "age";
                Log.d("input", "ageeeeee");
            } else if (input.startsWith("nom")) {
                value = input.substring(3).trim().substring(1).trim();
                Log.d("input", "nommmmmm");
                key = "nom";
            } else if (input.startsWith("prenom")) {
                value = input.substring(6).trim().substring(1).trim();
                Log.d("input", "prenommmmm");
                key = "prenom";
            } else if (input.startsWith("id")) {
                value = input.substring(2).trim().substring(1).trim();
                key = "id";
                Log.d("input", "iddddddd");
            } else {
                value = "";
                key = "";
                Log.d("input", "riennnnn");
            }
            Log.d("database", "Key: "+key);
            Log.d("database", "val: "+value);

            try {
                int number = Integer.parseInt(value);
                switch (key) {
                    case "age": cursor = db.lireAge(number); break;
                    case "id": cursor = db.lireId(number); break;
                    default: cursor = db.lireTable();
                }
            } catch (Exception e) {
                switch (key) {
                    case "nom": cursor = db.lireNom(value); break;
                    case "prenom": cursor = db.lirePrenom(value); break;
                    default: cursor = db.lireTable();
                }
            }

            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Database empty", Toast.LENGTH_SHORT).show();
                db.close();
                return;
            }

            buffer = new StringBuffer();
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {
                appendToBufferAllColumn(cursor);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            infoMaBase("resultat", buffer);
        });

    }

    private void infoMaBase(String title, StringBuffer buffer) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.setMessage(buffer.toString());
        alertDialog.show();
    }

    private void queryDb(Cursor cursor) {
    }
    // method rechereg par age
    // m recherche client par nom
    private void appendToBufferAllColumn(Cursor cursor) {
        buffer.append("Id: "+cursor.getInt(0)+ "\n");
        buffer.append("Nom: "+cursor.getString(1)+ "\n");
        buffer.append("Prenom: "+cursor.getString(2)+ "\n");
        buffer.append("Age: "+cursor.getInt(3)+ "\n");
        buffer.append("______________________\n");
    }
        // list view for sql showing
    // creer un editText pour rechercher NOM, PRENOM, AGE, ID, if empty, print all
}