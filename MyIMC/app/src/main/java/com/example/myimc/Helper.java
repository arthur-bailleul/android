package com.example.myimc;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Helper {
    // regles

    // JAVA

    // log.d("my_var", "msg"+value)

    // var
    // snake_case
    // type_name
    // button_result_imc

    // method
    // camelCase

    // XML

    // id
    // name_activity_type_name
    // activity_menu_button_imc_result
    public static ArrayList<Imc> getListImc(SqliteMaDataBase db) {

        Cursor cursor = db.lireTableIMC();

        if (cursor == null) {
            Log.d("database", "Pas d'imc stocker dans la base de donnee");
            return null;
        }

//        StringBuffer buffer = new StringBuffer();
        ArrayList<Imc> imcs = new ArrayList<>();

        cursor.moveToFirst();

        Imc imc = null;

        while(!cursor.isAfterLast()) {
            int taille = cursor.getInt(1);
            int poids = cursor.getInt(2);
            double imc_val = cursor.getDouble(3);
            String date = cursor.getString(4);

            imc = new Imc(taille, poids, imc_val, date);

            imcs.add(imc);
//            db.appendToBufferAllColumnForIMC(cursor, buffer);

            cursor.moveToNext();
        }

        cursor.close();

        return imcs;
    }
}

