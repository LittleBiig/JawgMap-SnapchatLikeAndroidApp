//package com.littlebiig.snapchat;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by Little on 26/10/2017.
// */
//
//public class mapActivity {
//
//    private Button mlaunchCameraButton;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mlaunchCameraButton = (Button) findViewById(R.id.map_launchCamera_button);
//
//        mlaunchCameraButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent captureActivity = new Intent(getActivity(), captureFragment.class);
//                startActivity(captureActivity);
//            }
//            });
//
//}