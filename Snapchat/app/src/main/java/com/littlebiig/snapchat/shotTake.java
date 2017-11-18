package com.littlebiig.snapchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * Created by Little on 22/11/2017.
 */

public class shotTake extends AppCompatActivity {

    private Button mCapturePhotoButton;
    private Button mCaptureVideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shot_take);
        mCapturePhotoButton = (Button) findViewById(R.id.shot_take_capturePhoto_button);
        mCaptureVideoButton = (Button) findViewById(R.id.shot_take_captureVideo_button);


        mCapturePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takePhoto (AXEL)
                Intent modifyActivity = new Intent(shotTake.this, modifyFragment.class);
                startActivity(modifyActivity);
            }
        });

        mCaptureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takeVideo (AXEL)
                Intent modifyActivity = new Intent(shotTake.this, modifyFragment.class);
                startActivity(modifyActivity);
            }
        });


    }
}
