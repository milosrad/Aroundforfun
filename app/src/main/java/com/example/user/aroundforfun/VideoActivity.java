package com.example.user.aroundforfun;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by User on 7.8.2016.
 */
public class VideoActivity extends Activity implements SurfaceHolder.Callback {

    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    String stringPath = "/sdcard/VID_2151124_213631.3gp";

    boolean hasActiveHolder=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        /*surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(176, 144);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();


        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);

        try {
            mediaPlayer.setDataSource(stringPath);
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mediaPlayer.start(); */
        playVideo();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        synchronized (this) {
            mediaPlayer.setDisplay(surfaceHolder);
            hasActiveHolder = true;
            this.notifyAll();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        synchronized (this) {
            hasActiveHolder = false;


                this.notifyAll();

        }

    }

    public void playVideo() {
        mediaPlayer = new MediaPlayer();
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceview);
        try {
            mediaPlayer.setDataSource(stringPath);
            SurfaceHolder sh = surfaceView.getHolder();
            synchronized (this) {
                while (!hasActiveHolder) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        //Print something
                    }
                }
                mediaPlayer.setDisplay(sh);
                mediaPlayer.prepare();
            }
            /*mp.setDisplay(sh);//***----the exception occured here***
            mp.prepare(); */
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
