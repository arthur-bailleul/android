package com.example.recyclerview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MonAdapteur monAdapteur;
    private RecyclerView recyclerView;
    private List<Items> mesItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_1);
        mesItems = new ArrayList<>();

        var id = R.drawable.chat1;

        for(int i = 0; i < 8; i++) {
            mesItems.add(new Items(id+i, "chat"+i, "description ..."));
        }

        mesItems.add(new Items(R.drawable.chat1, "chat1", "description ..."));
        mesItems.add(new Items(R.drawable.chat2, "chat1", "description ..."));
        mesItems.add(new Items(R.drawable.chat1, "chat1", "description ..."));
        mesItems.add(new Items(R.drawable.chat1, "chat1", "description ..."));
        mesItems.add(new Items(R.drawable.chat1, "chat1", "description ..."));
        mesItems.add(new Items(R.drawable.chat1, "chat1", "description ..."));

        Log.d("mesItems", mesItems.toString());

        monAdapteur = new MonAdapteur(mesItems, this);

//
        int orientation = getResources().getConfiguration().orientation;

        if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }



//        OrientationEventListener listener = new OrientationEventListener(this) {
//            @Override
//            public void onOrientationChanged(int orientation2) {
////                Log.d("ANGLE", String.valueOf(orientation));
//                int orientation = getResources().getConfiguration().orientation;
//                if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
//                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
//                } else {
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                }
//            }
//        };
//
//        listener.enable();

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,1);
//        recyclerView.setLayoutManager(linearLayoutManager);


//        var simpleCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            }
//        };
//
//        new ItemTouchHelper(simpleCallBack).attachToRecyclerView(recyclerView);
//        var delete = new EffetSwipe( this, monAdapteur, mesItems, ItemTouchHelper.LEFT);
        var callback = new EffetSwipe( this, monAdapteur, mesItems, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
//        new ItemTouchHelper(delete).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(monAdapteur);
    }
}