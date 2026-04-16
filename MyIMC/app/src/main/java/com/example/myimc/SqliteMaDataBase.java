package com.example.myimc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

public class SqliteMaDataBase extends SQLiteOpenHelper {
    public static final String DB_NAME = "maBase.db";
    public static final int DB_VERSION = 5;

    // Table IMC
    public static final String TABLE_IMC = "T_IMC";
    public static final String T_IMC_COL0 = "idIMC";
    public static final String T_IMC_COL1 = "taille";
    public static final String T_IMC_COL2 = "poids";
    public static final String T_IMC_COL3 = "imc";
    public static final String T_IMC_COL4 = "date";

    // Table ACTIV_SPORTIVE
    public static final String TABLE_ACTIV_SPORTIVE = "T_ActivSportive";
    public static final String T_ACTIV_SPORTIVE_COL0 = "idActivSportive";
    public static final String T_ACTIV_SPORTIVE_COL1 = "nomActivite";
    public static final String T_ACTIV_SPORTIVE_COL2 = "date";
    public static final String T_ACTIV_SPORTIVE_COL3 = "duree";
    public static final String T_ACTIV_SPORTIVE_COL4 = "commentaire";
    // afficher list: DATE - NOM - DUREE
    // trier par date

    public SqliteMaDataBase(Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABLE ActivSportive

        String string_sql_activSportive = "CREATE TABLE " + TABLE_ACTIV_SPORTIVE +
                " ("
                + T_ACTIV_SPORTIVE_COL0 + " integer primary key autoincrement, "
                + T_ACTIV_SPORTIVE_COL1 + " text not null, "
                + T_ACTIV_SPORTIVE_COL2 + " text not null, "
                + T_ACTIV_SPORTIVE_COL3 + " integer not null, "
                + T_ACTIV_SPORTIVE_COL4 + " text not null);";
        Log.d("database", "string_sql: " + string_sql_activSportive);
        db.execSQL(string_sql_activSportive);
        Log.d("database", "Creation de la Base de donnees: " + TABLE_ACTIV_SPORTIVE + " : OK");

       // TABLE IMC

        String string_sql_imc = "CREATE TABLE " + TABLE_IMC +
                " ("
                + T_IMC_COL0 + " integer primary key autoincrement, "
                + T_IMC_COL1 + " integer not null, "
                + T_IMC_COL2 + " integer not null, "
                + T_IMC_COL3 + " float not null, "
                + T_IMC_COL4 + " text not null);";
        Log.d("database", "string_sql: " + string_sql_imc);
        db.execSQL(string_sql_imc);
        Log.d("database", "Creation de la Base de donnees: " + TABLE_IMC + " : OK");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == newVersion) {
            Log.d("database", "no upgrade of the database");
           return;
        }
        Log.d("database", "upgrade of the database");

        String string_sql = "DROP TABLE IF EXISTS " + TABLE_ACTIV_SPORTIVE + ";";
        db.execSQL(string_sql);

        // Table IMC

        String string_sql_imc = "DROP TABLE IF EXISTS " + TABLE_IMC + ";";
        db.execSQL(string_sql_imc);

        onCreate(db);
    }

    public void insertionActivSportive(String nom, String date, int duree, String commentaire) {
        SQLiteDatabase db = this.getWritableDatabase();

        var values = new ContentValues();

        values.put(T_ACTIV_SPORTIVE_COL1, nom);
        // format: YEAR-mm-dd
        values.put(T_ACTIV_SPORTIVE_COL2, date);
        values.put(T_ACTIV_SPORTIVE_COL3, duree);
        values.put(T_ACTIV_SPORTIVE_COL4, commentaire);

        db.insert(TABLE_ACTIV_SPORTIVE, null, values);

        Log.d("database", "insertion ActivSportive: OK");

        // safe insert for exec
//        String sql = "INSERT INTO sport (nom, date, duree, commentaire) VALUES (?, ?, ?, ?)";
//        db.execSQL(sql, new Object[]{nom, date, duree, commentaire});
    }
    public void insertionIMC(int taille, int poids, double imc, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        var values = new ContentValues();

        values.put(T_IMC_COL1, taille);
        values.put(T_IMC_COL2, poids);
        values.put(T_IMC_COL3, imc);
        values.put(T_IMC_COL4, date);

        db.insert(TABLE_IMC, null, values);

        Log.d("database", "insertion IMC: OK");
    }

    public Cursor lireTableIMC() {
        SQLiteDatabase db = this.getReadableDatabase();
        String string_sql = "select * from " + TABLE_IMC;
        Log.d("database", "string_sql lecture: " + string_sql);
        Cursor cursor = db.rawQuery(string_sql, null);
        Log.d("database", "lecture IMC: OK");
        Log.d("database", "value cursor: " + cursor);
        return cursor;
    }

    public Cursor lireTableActivSportive() {
        SQLiteDatabase db = this.getReadableDatabase();
        String string_sql = "select * from " + TABLE_ACTIV_SPORTIVE;
        Log.d("database", "string_sql lecture: " + string_sql);
        Cursor cursor = db.rawQuery(string_sql, null);
        Log.d("database", "lecture ActivSportive: OK");
        return cursor;
    }
    public void infoMaBaseActivSportive(String title, StringBuffer buffer, Context ctx) {
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setPositiveButton("Ok", null)
                .setMessage(buffer)
                .show();
    }
    public void infoMaBaseIMC(String title, StringBuffer buffer, Context ctx) {
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setPositiveButton("Ok", null)
                .setMessage(buffer)
                .show();
    }
    public void appendToBufferAllColumnForActivSportive(Cursor cursor, StringBuffer buffer) {
        buffer.append("Id: ").append(cursor.getInt(0)).append("\n");
        buffer.append("Nom: ").append(cursor.getString(1)).append("\n");
        buffer.append("Date: ").append(cursor.getString(2)).append("\n");
        buffer.append("Duree: ").append(cursor.getInt(3)).append("m\n");
        buffer.append("Commentaire: ").append(cursor.getString(4)).append("\n");
        buffer.append("______________________\n");
    }
    public void appendToBufferAllColumnForIMC(Cursor cursor, StringBuffer buffer) {
        buffer.append("Taille: ").append(cursor.getInt(1)).append("\n");
        buffer.append("Poids: ").append(cursor.getInt(2)).append("\n");
        buffer.append("IMC: ").append(cursor.getDouble(3)).append("\n");
        buffer.append("Date: ").append(cursor.getString(4)).append("\n");
        buffer.append("______________________\n");
    }
}
