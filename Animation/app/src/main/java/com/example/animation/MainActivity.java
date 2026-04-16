package com.example.animation;

import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView chat_blanc;
    private ImageView chat_marron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat_blanc = findViewById(R.id.chat_blanc);
        chat_marron = findViewById(R.id.chat_marron);

//        var rotate_right = new RotateAnimation(0, 90,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f
//        );
//
//        rotate_right.setDuration(4_000);
//        rotate_right.setInterpolator(new BounceInterpolator());
//
//        rotate_right.setRepeatCount(Animation.INFINITE);
//        chat_blanc.startAnimation(rotate_right);
//
        var rotate_left = new RotateAnimation(0, -360,
                Animation.RELATIVE_TO_SELF, 0.f, // 0 = left, 1 = right, 0.5 = center
                Animation.RELATIVE_TO_SELF, 0.f // 0 = top, 1 = bottom, 0.5 = center
        );

        rotate_left.setDuration(4000);
        rotate_left.setInterpolator(new AccelerateInterpolator());
//        rotate_left.setInterpolator(new LinearInterpolator());

        rotate_left.setRepeatCount(Animation.INFINITE);
        chat_marron.startAnimation(rotate_left);

        new Rotation(chat_blanc,0, 360, 0.5f, 0.5f)
                .setDuration(5000)
                .setInfiniteAnimation()
                .setInterpolation("linear")
                .build();
        // on aurait pu extends RotateAnimation
        // on aurait pu faire une method static?
    }
}