package com.example.user.aroundforfun.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.user.aroundforfun.R;

/**
 * Created by User on 7.8.2016.
 */
public class VideoViewActivity extends Activity {

    String stringPath = "sdcard/Test.mp4";
    String stringPath1 = "sdcard/NaKosovu.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

       /* VideoView videoView=(VideoView)findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);

        mediaController.setAnchorView(videoView);

        Uri video= Uri.parse(stringPath1);
        videoView.setVideoURI(video);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start(); */

        playVideo();



    }

    private void playVideo(){

        VideoView videoView=(VideoView)findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);

        mediaController.setAnchorView(videoView);

        Uri video= Uri.parse(stringPath1);
        videoView.setVideoURI(video);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();


    }
}
