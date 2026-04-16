package com.example.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class RotationExtended extends RotateAnimation {
    public RotationExtended(float fromDegree, float toDegree, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue, ImageView img, int duree) {
        super(fromDegree, toDegree,pivotXType, pivotXValue, pivotYType, pivotYValue); // constructeur du parent

        setDuration(duree);
        setInterpolator(new LinearInterpolator());
        img.startAnimation(this);
    }
}
