package com.littlebiig.snapchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class shot_modifyFragment extends AppCompatActivity {

    private Button mCancelButton;
    private Button mConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shot_modify_fragment);


        mCancelButton = (Button) findViewById(R.id.modify_cancel_button);
        mConfirmButton = (Button) findViewById(R.id.modify_confirm_button);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takePhoto (AXEL)
                onBackPressed();
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                function takeVideo (AXEL)
                Intent confirmActivity = new Intent(shot_modifyFragment.this, shot_confirmFragment.class);
                startActivity(confirmActivity);
            }
        });

    }
}
