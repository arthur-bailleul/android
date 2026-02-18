package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button btn;
    private Intent intent;
    private Handler handler;
//    private Context context; // c'est juste le scope

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        editText = findViewById(R.id.editText_input);

        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(MainActivity.this, Activity3.class);
                intent.putExtra("dataSend", "send auto");
                startActivity(intent);
            }
        },  10_000); // runable that act like a click after x time

        // proteger mainActivity avec un progressbar de chargement et switch sur un autre pour que si l'activite bug,
        // on peut mettre une autre activite mais on ne peut pas avec mainActivity

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = editText.getText().toString().trim();

                if (input.isEmpty()) return;

//                Intent intent = new Intent(MainActivity.super.getApplication(), Activity2.class);
//                Intent intent = new Intent(context, Activity2.class);
                intent = new Intent(MainActivity.this, Activity3.class);
                intent.putExtra("dataSend", input);
                startActivity(intent);
                finish(); // empeche de revenir en arriere

                // getApplicationContext() plus haut niveau, context of the app
                // MyActivity.this context of an activity
            }
        });





        // layout gravity: margin, box itself
        // gravity: padding, inside box
    }
}
/*

bundle (memoire transmis entre activite)



//Intent in = new Intent(this, activite2);
// on peut call une autre app ex nav



handler: attend x temps




 */
