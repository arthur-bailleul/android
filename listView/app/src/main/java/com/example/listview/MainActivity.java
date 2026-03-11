package com.example.listview;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("aaaaa", "chat1: " + String.valueOf(R.drawable.chat1));
        Log.d("aaaaa", "chat7: " + String.valueOf(R.drawable.chat7));

        int nb_img = R.drawable.chat7 - R.drawable.chat1;
        var listItem = new ArrayList<HashMap<String,String>>();

        for(int i = 0; i <= nb_img; i++) {
            var map = new HashMap<String, String>();

            map.put("titre", "chat "+(i+1));
            map.put("description", "dsroatnraisotnraortno");
            map.put("img", String.valueOf(R.drawable.chat1+i));

            Log.d("aaaaa", "chat" + (i+1) + ": " + String.valueOf(R.drawable.chat1+i));

            listItem.add(map);
        }
        listView = findViewById(R.id.list_view);

        var map = new HashMap<String, String>();

        map.put("titre", "chat 1");
        map.put("description", "dsroatnraisotnraortno");
        map.put("img", String.valueOf(R.drawable.test));

        Log.d("aaaaa", "chat1: " + String.valueOf(R.drawable.test));

        listItem.add(map);

        Log.d("aaaaa", String.valueOf(listItem));

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this.getBaseContext(),
                listItem,
                R.layout.layout_list_item,
                new String[] {"img", "titre", "description"},
                new int[] {R.id.img, R.id.titre, R.id.description}
        );
        listView.setAdapter(simpleAdapter);
    }
}