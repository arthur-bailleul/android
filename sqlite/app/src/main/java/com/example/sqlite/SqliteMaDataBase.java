package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqliteMaDataBase extends SQLiteOpenHelper {
    public static final String BASE_NOM = "MaBase.db";
    public static final int BASE_VERSION = 2;

    public static final String NOM_TABLE = "T_clients";
    public static final String COL0 = "IdClient";
    public static final String COL1 = "NOM";
    public static final String COL2 = "PRENOM";
    public static final String COL3 = "AGE";



    public SqliteMaDataBase(Context context) {
        super(context, BASE_NOM, null, BASE_VERSION); // constructeur de la class herite
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // done only if db not exist
        String string_sql = "CREATE TABLE " + NOM_TABLE +
                " ("
                + COL0 + " integer primary key autoincrement,"
                + COL1 + " text not null,"
                + COL2 + " text not null,"
                + COL3 + " integer not null);";
        Log.d("DataBase", "string_sql: " + string_sql);
        db.execSQL(string_sql);
        Log.d("DataBase", "Creation de la Base de donnees OK " + NOM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // runned only when version !=
        String string_sql = "DROP TABLE IF EXISTS " + NOM_TABLE + ";";
        db.execSQL(string_sql);
        onCreate(db);
        // miss some thing
    }

    public void insertionClients(String NOM, String PRENOM, Integer AGE) {
        NOM = NOM.replace("'", " ");
        PRENOM = PRENOM.replace("'", " ");

        NOM = NOM.toLowerCase();
        PRENOM = PRENOM.toLowerCase();

        String string_sql = "INSERT INTO " + NOM_TABLE + "("
                 + COL1 + "," + COL2 + "," + COL3 +
                ")" + " values ('" + NOM + "','" + PRENOM + "'," + AGE + ");";

        Log.d("DataBase", "string_sql insert: " + string_sql);
        getWritableDatabase().execSQL(string_sql);
        Log.d("DataBase", "insertionClients, insertion OK");
    }

    public  void fillDatabase() {
        for(int i = 0; i < 40; i++) {
            insertionClients("aaaa"+i, "bbbb"+i, i);
            insertionClients("aaaa"+i, "bbbb"+i, i);
        }
    }

    public Cursor lireTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + NOM_TABLE , null);
        //rawquery + efficace que qu'Execsql
        return cursor;
    }

    public Cursor lireAge(int age) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + NOM_TABLE + " where "+COL3+" = " + age , null);
        //rawquery + efficace que qu'Execsql
        return cursor;
    }
    public Cursor lireNom(String nom) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + NOM_TABLE + " where "+COL1+" = '" + nom + "'", null);
        //rawquery + efficace que qu'Execsql
        return cursor;
    }

    public Cursor lirePrenom(String prenom) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + NOM_TABLE + " where "+COL2+" = '" + prenom + "'", null);
        //rawquery + efficace que qu'Execsql
        return cursor;
    }
    public Cursor lireId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + NOM_TABLE + " where "+COL0+" = " + id, null);
        //rawquery + efficace que qu'Execsql
        return cursor;
    }
}
