package com.littlebiig.snapchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class captureFragment extends AppCompatActivity {

    private Button mCapturePhotoButton;
    private Button mCaptureVideoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);
        mCapturePhotoButton = (Button) findViewById(R.id.welcomeScreen_capturePhoto_button);
        mCaptureVideoButton = (Button) findViewById(R.id.welcomeScreen_captureVideo_button);

        mCapturePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takePhoto (AXEL)
                Intent modifyActivity = new Intent(captureFragment.this, modifyFragment.class);
                startActivity(modifyActivity);
            }
        });

        mCaptureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takeVideo (AXEL)
                Intent modifyActivity = new Intent(captureFragment.this, modifyFragment.class);
                startActivity(modifyActivity);
            }
        });


    }

    };

