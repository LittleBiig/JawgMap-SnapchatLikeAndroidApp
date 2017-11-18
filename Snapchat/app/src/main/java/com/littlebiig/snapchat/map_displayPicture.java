package com.littlebiig.snapchat;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;

//import com.tbuonomo.jawgmapsample.R;

public class map_displayPicture extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");


        //img.setImageResource(getResources().getIdentifier(id, "drawable", getPackageName()));
        setContentView(R.layout.map_display_picture);
        ImageView img= (ImageView) findViewById(R.id.imageView);
        //img.setImageResource(R.drawable.image);
        img.setImageResource(getResources().getIdentifier(id, "drawable", getPackageName()));
    }

}
