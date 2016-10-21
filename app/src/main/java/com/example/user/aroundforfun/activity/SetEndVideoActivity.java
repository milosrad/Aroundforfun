package com.example.user.aroundforfun.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.aroundforfun.R;

/**
 * Created by User on 11.8.2016.
 */
public class SetEndVideoActivity extends Activity {

    private Button mSetEndVideoButton;
    private EditText mTextVideo;

    static final int READ_BLOCK_SIZE = 100;

    int SELECT_VIDEO_REQUEST=100;
    String selectedVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_end_video);

        initComponents();
        addListeners();
    }


    private void initComponents(){

        mSetEndVideoButton=(Button)findViewById(R.id.buttonsetendvideoactivity);

        mTextVideo=(EditText)findViewById(R.id.edittextsetendvideo);



    }

    private void addListeners(){


        mSetEndVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SettingsActivityCounter.videopath=selectedVideoPath;
                finish();


            }
        });

        mTextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectVideoFromGallery();


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


    public void selectVideoFromGallery()
    {
        Intent intent;
        if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        }
        else
        {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        }
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(intent,SELECT_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == SELECT_VIDEO_REQUEST && resultCode == RESULT_OK)
        {
            if(data.getData()!=null)
            {
                selectedVideoPath = getPath(data.getData());
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed to select video" , Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


}
