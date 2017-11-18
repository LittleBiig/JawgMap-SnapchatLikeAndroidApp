package com.littlebiig.snapchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;


/**
 * Created by Little on 22/11/2017.
 */

public class shot_Take extends Activity {


    public static final int REQUEST_CAPTURE = 1;
    protected ImageView resultphoto;
    protected VideoView resultvideo;
    static final int cam_request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shot_take); //envoie la vue définie dans main_activity

        Button click = (Button)findViewById(R.id.shot_take_capturePhoto_button);
        resultphoto = (ImageView)findViewById(R.id.ImageView);

        Button click2 = (Button)findViewById(R.id.shot_take_captureVideo_button);
        resultvideo = (VideoView)findViewById(R.id.VideoView);

        click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f2=getFile();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f2));
                startActivityForResult(intent, cam_request);
            }
        });

        click2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                File f2=getFile();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f2));
                startActivityForResult(intent, cam_request);
            }
        });

        if(!hasCamera()) //faisable bouton si pas les droits
        {
            click.setEnabled(false);
            click2.setEnabled(false);

        }
    }

    public boolean hasCamera() //si il y a une caméra ça retourne true donc le if est bon au dessus
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public File getFile() //stockage de la photo et création de fichier sur carte sd si besoin
    {
        File f1=new File("sdcard");
        if (!f1.exists())
        {
            f1.mkdir();
        }
        File img = new File(f1,"img.jpg");
        return img;
    }



    public void launchCamera(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }


    @Override //construite par le logiciel
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            resultphoto.setImageBitmap(photo);
        }
    }
}
