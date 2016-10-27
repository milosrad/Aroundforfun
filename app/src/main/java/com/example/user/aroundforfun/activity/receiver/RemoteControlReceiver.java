package com.example.user.aroundforfun.activity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.user.aroundforfun.activity.MainActivity;

/**
 * Created by User on 9.8.2016.
 */
public class RemoteControlReceiver extends BroadcastReceiver {

    private MainActivity mainActivity;





    public RemoteControlReceiver(){
        super();
    //    mainActivity=maContext;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (KeyEvent.KEYCODE_MEDIA_PLAY == event.getKeyCode()) {
                // Handle key press.
                mainActivity.countupwards();
            }

            if(KeyEvent.KEYCODE_CHANNEL_UP== event.getKeyCode()){

                mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_CHANNEL_DOWN== event.getKeyCode()){

                 mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_HEADSETHOOK== event.getKeyCode()){

                mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }


            if(KeyEvent.KEYCODE_MEDIA_NEXT== event.getKeyCode()){

                mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_MEDIA_STOP== event.getKeyCode()){

                   mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }


            if(KeyEvent.KEYCODE_MINUS== event.getKeyCode()){

                mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_MUTE== event.getKeyCode()){

                   mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_MEDIA_PREVIOUS== event.getKeyCode()){

                        mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_MEDIA_PAUSE== event.getKeyCode()){

                        mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }

            if(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE== event.getKeyCode()){

                        mainActivity.countupwards();
                Toast.makeText(context,"Pritisnuto",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setMainActivity(MainActivity mainActivity){

        this.mainActivity=mainActivity;
    }
}
