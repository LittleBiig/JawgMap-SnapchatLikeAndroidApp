package com.littlebiig.snapchat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by trunk on 25/10/2017.
 */

public class getLocation implements ActivityCompat.OnRequestPermissionsResultCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public Context context;
    public Activity activity;
    public LocationListener locationListener;
    public Location lastLocation;
    getLocation(Context context,Activity activity) {
        this.context=context;
        this.activity=activity;

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (false) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this.context)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            private Activity activity=this.activity;

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(this.activity,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this.activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager)this.context.getSystemService(Context.LOCATION_SERVICE);
                    ArrayList<LocationProvider> providers = new ArrayList<LocationProvider>();
                    //ArrayList<String> names = locationManager.getProviders(true);
                    Criteria critere = new Criteria();
                    // Pour indiquer la précision voulue
                    // On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
                    critere.setAccuracy(Criteria.ACCURACY_FINE);
                    // Est-ce que le fournisseur doit être capable de donner une altitude ?
                    critere.setAltitudeRequired(false);
                    // Est-ce que le fournisseur doit être capable de donner une direction ?
                    critere.setBearingRequired(false);
                    // Est-ce que le fournisseur peut être payant ?
                    critere.setCostAllowed(false);
                    // Pour indiquer la consommation d'énergie demandée
                    // Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
                    critere.setPowerRequirement(Criteria.POWER_HIGH);
                    // Est-ce que le fournisseur doit être capable de donner une vitesse ?
                    critere.setSpeedRequired(false);

                    String bestProvider=locationManager.getBestProvider(critere,true);

                    int permissionCheck = ContextCompat.checkSelfPermission(this.activity,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);

                    //final Location location=locationManager.getLastKnownLocation(bestProvider);
                    locationManager.requestLocationUpdates(bestProvider, 10000, 10, this.locationListener=new LocationListener() {

                        public Location location;

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }

                        @Override
                        public void onLocationChanged(Location location) {
                            lastLocation=location;

                        }
                    });


                } else {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //FERMER LAPPLI
                }
                return ;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}

