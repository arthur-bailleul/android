package com.example.lecteurvideo;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        String monUrl = "https://file-examples.com/storage/febe050bee69c3fe89f3c08/2020/03/file_example_WEBM_480_900KB.webm";
//        Uri uri_local = Uri.parse("android.resource://"
//                + getPackageName()
//                + "/"
//                + R.raw.ma_video
//        );
        Uri uri = Uri.parse(monUrl);
        videoView.setVideoURI(uri);
        videoView.start();

    }
}