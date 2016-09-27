package com.example.user.aroundforfun;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by User on 11.8.2016.
 */
public class SettingsActivityCounter extends Activity {

    private Button mSaveandGoToMainWindow,mResetCurrentCounterStatus,mSetCounter;
    private Button mResetAll,mSetCounterLimit,mSetProgramBackground;
    private Button mSetAlarmSound,mSetEndVideo,mSetPassword;
    private Button mExit;


    private boolean isResetAllPressed;

    public static int countervalue;
    public static int counterlimitvalue;

    public static Bitmap backgroundselectedimage;

    public static String videopath;
    public static String audioopath;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();
        addListeners();

        countervalue=0;
    }


    private void initComponents(){

        mSaveandGoToMainWindow=(Button)findViewById(R.id.buttonsavesettingsgotomainwindow);
        mResetCurrentCounterStatus=(Button)findViewById(R.id.buttonresetcurrentcounterstatus);
        mSetCounter=(Button)findViewById(R.id.buttonsetcounter);
        mResetAll=(Button)findViewById(R.id.buttonresetall);
        mSetCounterLimit=(Button)findViewById(R.id.buttonsetcounterlimit);
        mSetProgramBackground=(Button)findViewById(R.id.buttonsetprogrambackground);
        mSetAlarmSound=(Button)findViewById(R.id.buttonsetalarmsound);
        mSetEndVideo=(Button)findViewById(R.id.buttonsetendvideo);
        mSetPassword=(Button)findViewById(R.id.buttonsetpassword);
        mExit=(Button)findViewById(R.id.buttonexit);

        isResetAllPressed=false;

    }

    private void addListeners(){

        mSaveandGoToMainWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isResetAllPressed){mSaveandGoToMainWindow.setClickable(false);}

                else {

                    Intent mainintent = new Intent(SettingsActivityCounter.this, MainActivity.class);
                    mainintent.putExtra("value", countervalue);
                    startActivity(mainintent);
                }

            }
        });

        mResetCurrentCounterStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MainActivityCounter.counter.setNumber(0);

            }
        });

        mSetCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent setCounterIntent=new Intent(SettingsActivityCounter.this,SetCounterActivity.class);
                startActivity(setCounterIntent);

            }
        });

        mResetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isResetAllPressed=true;

                open(view);

            }
        });

        mSetCounterLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent setCounterLimitIntent=new Intent(SettingsActivityCounter.this,SetCounterLimitActivity.class);
                startActivity(setCounterLimitIntent);


            }
        });

        mSetProgramBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent setbackground= new Intent(SettingsActivityCounter.this,SetProgramBackgroundActivity.class);
                startActivity(setbackground);


            }
        });

        mSetAlarmSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alarmintent= new Intent(SettingsActivityCounter.this,SetAlarmSoundActivity.class);
                startActivity(alarmintent);


            }
        });

        mSetEndVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent videointent= new Intent(SettingsActivityCounter.this,SetEndVideoActivity.class);
                startActivity(videointent);

            }
        });

        mSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent passwordintent= new Intent(SettingsActivityCounter.this,SetPasswordActivity.class);
                startActivity(passwordintent);



            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isResetAllPressed=false;

                finishAffinity();

            }
        });




    }


    public void open(View view){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure that You want to reset all the settings ");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(SettingsActivityCounter.this,"You clicked yes button",Toast.LENGTH_LONG).show();

            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {

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

}

