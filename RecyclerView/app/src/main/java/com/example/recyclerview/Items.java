package com.example.recyclerview;

import android.widget.ImageView;
import android.widget.TextView;

public class Items {
    private int imageView_img;
    private String textView_titre;
    private String textView_description;

    public Items(int imageView_img, String textView_titre, String textView_description) {
        this.imageView_img = imageView_img;
        this.textView_titre = textView_titre;
        this.textView_description = textView_description;
    }


    public int getImageView_img() {
        return imageView_img;
    }

    public void setImageView_img(int imageView_img) {
        this.imageView_img = imageView_img;
    }

    public String getTextView_titre() {
        return textView_titre;
    }

    public void setTextView_titre(String textView_titre) {
        this.textView_titre = textView_titre;
    }

    public String getTextView_description() {
        return textView_description;
    }

    public void setTextView_description(String textView_description) {
        this.textView_description = textView_description;
    }
}
