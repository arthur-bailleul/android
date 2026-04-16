package com.example.animation;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class Rotation {
    private int duration;
    private Interpolator interpolator;
    private View img;
    private RotateAnimation rotate;
    private boolean is_infinite;

    public Rotation(View img, int start_angle, int end_angle, float pivot_x, float pivot_y) {
        this.img = img;
        rotate = new RotateAnimation(start_angle, end_angle,
                Animation.RELATIVE_TO_SELF, pivot_x, // 0 = left, 1 = right, 0.5 = center
                Animation.RELATIVE_TO_SELF, pivot_y // 0 = top, 1 = bottom, 0.5 = center
        );
    }

    public Rotation setDuration(int miliseconds) {
        rotate.setDuration(miliseconds);
        return this;
    }

    public Rotation setInterpolation(String type) {
        switch (type) {
            case "linear":
                rotate.setInterpolator(new LinearInterpolator());
                Log.d("rotation", "rotation lineaire");
                break;
            case "bounce":
                rotate.setInterpolator(new BounceInterpolator());
            default: System.out.println("default"); break;
        }
        return this;
    }

    public Rotation setInfiniteAnimation() {
        rotate.setRepeatCount(Animation.INFINITE);
        return this;
    }

    public RotateAnimation getRotateAnimation() {
        return rotate;
    }
    public void build() {
        img.startAnimation(rotate);
    }
}
