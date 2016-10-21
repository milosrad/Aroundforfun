package com.example.user.aroundforfun.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.aroundforfun.R;

/**
 * Created by User on 11.8.2016.
 */
public class SettingsActivity extends Activity {

    private Button mSaveandGoToMainWindow,mResetCurrentCounterStatus,mSetCounter;
    private Button mResetAll,mSetCounterLimit,mSetProgramBackground;
    private Button mSetAlarmSound,mSetEndVideo,mSetPassword;
    private Button mExit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();
        addListeners();
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

    }

    private void addListeners(){

        mSaveandGoToMainWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent mainintent=new Intent(SettingsActivity.this,MainActivity.class);

                startActivity(mainintent);

            }
        });

        mResetCurrentCounterStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               MainActivity.number=0;

            }
        });

        mSetCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                open(view);

            }
        });

        mResetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open(view);

            }
        });

        mSetCounterLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                open(view);

            }
        });

        mSetProgramBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent setbackground= new Intent(SettingsActivity.this,SetProgramBackgroundActivity.class);
                startActivity(setbackground);


            }
        });

        mSetAlarmSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alarmintent= new Intent(SettingsActivity.this,SetAlarmSoundActivity.class);
                startActivity(alarmintent);


            }
        });

        mSetEndVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent videointent= new Intent(SettingsActivity.this,SetEndVideoActivity.class);
                startActivity(videointent);

            }
        });

        mSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent passwordintent= new Intent(SettingsActivity.this,SetPasswordActivity.class);
                startActivity(passwordintent);



            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                Toast.makeText(SettingsActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();

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
