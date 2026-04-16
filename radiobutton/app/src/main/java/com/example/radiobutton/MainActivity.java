package com.example.radiobutton;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Switch aSwitch;
    private TextView textView;

    private SeekBar seekBar;
    private SeekBar seekBar2;

    private RatingBar ratingBar;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        textView = findViewById(R.id.textView);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false); // switch une fois
                aSwitch.setChecked(true); //
                aSwitch.setClickable(true);

                boolean a = aSwitch.isActivated();
                boolean b = aSwitch.isChecked();
                boolean c = aSwitch.isClickable();
                boolean d = aSwitch.isEnabled();

                textView.setText("valeur du checked "+ String.valueOf(b));

                if (!view.isActivated()) {
                    textView.setText("On");
                    aSwitch.setText("On");
                    view.setActivated(true);
                } else {
                    textView.setText("Off");
                    aSwitch.setText("Off");

                    view.setActivated(false);
                }
            }
        });

        // ----------------- seeker bar

        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);

        seekBar.setMax(100);
        seekBar.setProgress(10);
        seekBar2.setProgress(0);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int i, boolean b) {
                seekBar.setProgress(i*10);
                textView.setTextSize(i*10);
                ratingBar.setNumStars(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar s) {
                s.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onStopTrackingTouch(SeekBar s) {
                s.setBackgroundColor(Color.WHITE);
            }
        });


        // ------------- Rating bar

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setRating(4.5f);
        ratingBar.setMax(5);
//        ratingBar.getRating();
        ratingBar.setNumStars(5);
//        ratingBar.setStepSize(2)
//        ratingBar.setProgress(50);

        ratingBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setNumStars(Float.floatToIntBits(v));
            }
        });

        // ---------------- SearchView

        searchView = findViewById(R.id.search_view);

        searchView.setQueryHint("UWU");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
        });


        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int i) {
                return false;
            }

            @Override
            public boolean onSuggestionSelect(int i) {
                return false;
            }
        });
    }
}