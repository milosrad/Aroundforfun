package com.example.user.aroundforfun;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Button mButtonSetup,mButtonCount;
    private TextView mCounter;
    public  static int number;
    private Animation fade_in_animation;
    private RelativeLayout mRoot;
    private VideoView videoView;

    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;

    String stringPath = "sdcard/Test.mp4";
    String stringPath1 = "sdcard/NaKosovu.3gp";
    String stringPath2 = "sdcard/media/audio/notications/facebook_ringtone_pop.m4a";


    private SoundPool mSoundPool;
    private int mSoundID;
    private boolean isLoaded = false;

    private RemoteControlReceiver myReceiver;

    IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);

    public static int counterlimit;

    public int getCounterlimit() {
        return counterlimit;
    }

    public static void setCounterlimit(int counterlimitsettings) {
        counterlimit = counterlimitsettings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setCounterlimit(10);


        initComponents();
        addListeners();
        
       

    /*    getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(176, 144);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer(); */



    }

    private void initComponents(){

        mButtonCount=(Button)findViewById(R.id.buttoncounter);
        mButtonSetup=(Button)findViewById(R.id.buttonsetup);
        mCounter=(TextView) findViewById(R.id.editTextcounter);
        mRoot=(RelativeLayout)findViewById(R.id.root);
        videoView=(VideoView)findViewById(R.id.videoView);

        fade_in_animation= AnimationUtils.loadAnimation(this,R.anim.fade_in);

        myReceiver=new RemoteControlReceiver();

        registerReceiver(myReceiver,filter);

        




    }

    private void addListeners(){

        mButtonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countupwards();

            }
        });

        mButtonSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onResume() {

        registerReceiver(myReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }





    private void countupwards(){

        number=Integer.parseInt(mCounter.getText().toString());

        number++;
        mCounter.setText(""+number);

        if (number%counterlimit==0){
            //mCounter.startAnimation(fade_in_animation);
            mRoot.startAnimation(fade_in_animation);

            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        /*    String soundAnimUrl = "/sdcard/ringtones/high_priority.wav";

            this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            mSoundID = mSoundPool
                    .load(getFile(Environment.DIRECTORY_RINGTONES, soundAnimUrl)
                            .getPath(), 1);
            mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId,
                                           int status) {
                    isLoaded = true;

                    // Play the sound when loaded
                    play();
                }
            }); */



        //    playSound();

        //    videoView.setVisibility(View.VISIBLE);

        //    playVideo();


         /*   Intent intent= new Intent(MainActivity.this,VideoViewActivity.class);
            startActivity(intent);
            finish(); */

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            Uri data = Uri.parse(stringPath1);
           // intent.setDataAndType(data, "video/mp4");
            intent.setDataAndType(data, "video/*");
            startActivity(intent);

         /*   surfaceView.setVisibility(View.VISIBLE);

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

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void playVideo(){


        MediaController mediaController= new MediaController(this);

        mediaController.setAnchorView(videoView);

        Uri video= Uri.parse(stringPath1);
        videoView.setVideoURI(video);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();


    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            windowCloseHandler.postDelayed(windowCloserRunnable, 250);
        }
    }

    private void toggleRecents() {
        Intent closeRecents = new Intent("com.android.systemui.recent.action.TOGGLE_RECENTS");
        closeRecents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        ComponentName recents = new ComponentName("com.android.systemui", "com.android.systemui.recent.RecentsActivity");
        closeRecents.setComponent(recents);
        this.startActivity(closeRecents);
    }

    private Handler windowCloseHandler = new Handler();
    private Runnable windowCloserRunnable = new Runnable() {
        @Override
        public void run() {
            ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

            if (cn != null && cn.getClassName().equals("com.android.systemui.recent.RecentsActivity")) {
                toggleRecents();
            }
        }
    };

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Log.d("Focus debug", "Focus changed !");

        if(!hasFocus) {
            Log.d("Focus debug", "Lost focus !");

            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    } */

    private void playSound(){



        Uri data=Uri.parse(stringPath2);

       // MediaPlayer player=MediaPlayer.create(getApplicationContext(),data);
       MediaPlayer player=new MediaPlayer();
        try {
            player.setDataSource(stringPath2);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();


    }

    private void play() {
        // Getting the user sound settings
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float actualVolume = (float) audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = actualVolume / maxVolume;

        // Is the sound loaded already?
        if (isLoaded) {
            mSoundPool.play(mSoundID, volume, volume, 1, 0, 1f);
            Log.d(MEDIA_PROJECTION_SERVICE, "Played sound");
        }
    }

    public File getFile(final String deviceFolderPath, final String dbFilePath) {

        // Create full path
        String picturePath = deviceFolderPath.concat(File.separator).concat(
                dbFilePath);

        // Create file
        File mFile = getExternalFilesDir(picturePath);

        return mFile;
    }


    private class RemoteControlReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
                KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                if (KeyEvent.KEYCODE_MEDIA_PLAY == event.getKeyCode()) {
                    // Handle key press.
                    countupwards();
                }
            }
        }
    }

    
    
    

}
