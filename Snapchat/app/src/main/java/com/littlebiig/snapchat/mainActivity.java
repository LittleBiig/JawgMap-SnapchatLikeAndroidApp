package com.littlebiig.snapchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainActivity extends AppCompatActivity {

    private Button mCapturePhotoButton;
    private Button mCaptureVideoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mCapturePhotoButton = (Button) findViewById(R.id.mainActivity_capturePhoto_button);
        mCaptureVideoButton = (Button) findViewById(R.id.mainActivity_captureVideo_button);

        mCapturePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takePhoto (AXEL)
                Intent modifyActivity = new Intent(mainActivity.this, modifyFragment.class);
                startActivity(modifyActivity);
            }
        });

        mCaptureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takeVideo (AXEL)
                Intent modifyActivity = new Intent(mainActivity.this, modifyFragment.class);
                startActivity(modifyActivity);
            }
        });


    }

    };

