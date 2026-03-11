package com.example.myimc;

import static com.example.myimc.Activity_menu.setButtonGoToAny;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity_historique_imc extends AppCompatActivity {
    private ListView listView;
    private HashMap<String,String> map;
    private ArrayList<HashMap<String,String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_imc);

        listView = findViewById(R.id.activite_historique_imc_listView);

        setButtonGoToAny(this, findViewById(R.id.historique_imc_floatingActionButton), Activity_result_imc.class);



        SqliteMaDataBase db = new SqliteMaDataBase(this);

        listItem = new ArrayList<>();
        ArrayList<Imc> imcs = Helper.getListImc(db);

        db.close();

        Log.d("historique_imc", String.valueOf(imcs));


        if (imcs == null) {
            Log.d("historique", "imcs null");
            return;
        }

        map = new HashMap<>();

        map.put("taille", "Taille");
        map.put("poids", "Poids");
        map.put("imc", "IMC");
        map.put("date", "Date");

        listItem.add(map);

       for(var imc: imcs) {
           map = new HashMap<>();

           map.put("taille", String.valueOf(imc.getTaille()));
           map.put("poids", String.valueOf(imc.getPoids()));
           map.put("imc", String.valueOf(imc.getIMC()));
           map.put("date", imc.getDate());

           listItem.add(map);
       }

        var simpleAdapter = new SimpleAdapter(
                this.getBaseContext(),
                listItem,
                R.layout.historique_item,
                new String[] { "taille", "poids", "imc", "date" },
                new int[] { R.id.historique_item_taille, R.id.historique_item_poids, R.id.historique_item_imc, R.id.historique_item_date }
        );
       listView.setAdapter(simpleAdapter);


    }
}