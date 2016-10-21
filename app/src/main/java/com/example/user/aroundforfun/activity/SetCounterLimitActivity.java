package com.example.user.aroundforfun.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.aroundforfun.R;

/**
 * Created by User on 11.8.2016.
 */
public class SetCounterLimitActivity extends Activity {

    private Button mSetCounterLimitButton;
    private EditText mTextLimit;

    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_counter_limit);

        initComponents();
        addListeners();
    }


    private void initComponents(){

        mSetCounterLimitButton=(Button)findViewById(R.id.buttonsetcounterlimitactivity);

        mTextLimit=(EditText)findViewById(R.id.edittextsetcounterlimit);



    }

    private void addListeners(){


        mSetCounterLimitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int limitvalue=Integer.parseInt(mTextLimit.getText().toString());
                SettingsActivityCounter.counterlimitvalue=limitvalue;
                finish();

            }
        });

    }


    // write text to file
  /*  public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(mText.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            mText.setText(s);
            InputRead.close();
            Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
}
