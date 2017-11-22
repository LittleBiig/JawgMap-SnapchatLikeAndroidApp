package com.littlebiig.snapchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class mainActivity extends AppCompatActivity {

    private Button mCaptureMediaButton;
    private ImageButton mSeeStoryImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mCaptureMediaButton = (Button) findViewById(R.id.main_activity_captureMedia_button);
        mSeeStoryImageButton = (ImageButton) findViewById(R.id.main_activity_seeStory_button);


        mCaptureMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shotTake = new Intent(mainActivity.this, shotTake.class);
                startActivity(shotTake);
            }
        });

//        mSeeStory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                function see Stories
//                Intent modifyActivity = new Intent(mainActivity.this, modifyFragment.class);
//                startActivity(modifyActivity);
//            }
//        });


    }

    };

