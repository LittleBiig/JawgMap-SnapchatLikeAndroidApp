package com.littlebiig.snapchat;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


public class mainActivity extends AppCompatActivity {

    private Button mCaptureMediaButton;
    private ImageButton mSeeStoryImageButton;
    private MapView mapView;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final map_getLocation getLoca = new map_getLocation(this.getApplicationContext(), this);

        Mapbox.getInstance(this, "pk.eyJ1IjoicXVlbnRpbmRlYm91dCIsImEiOiJjajh6em13ZGkyZXQ3MzJwMjB3d2RpdDJxIn0.JNUGKO5NH1gMfVAXRIu2PQ");
        setContentView(R.layout.main_activity);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        mCaptureMediaButton = (Button) findViewById(R.id.main_activity_captureMedia_button);
        mSeeStoryImageButton = (ImageButton) findViewById(R.id.main_activity_seeStory_button);


        mCaptureMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shot_Take = new Intent(mainActivity.this, shot_Take.class);
                startActivity(shot_Take);
            }
        });

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {

                getLoca.checkLocationPermission();

                getLoca.locationManager.requestLocationUpdates(getLoca.bestLocationProvider, 10000, 10,getLoca.locationListener=new LocationListener() {

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
                        mapboxMap.clear();
                        mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(0, 0))
                                .title("image")
                        );
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(70, 0))
                                .title("image2")
                        );
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(mLatLng.getLatitude(), mLatLng.getLongitude()))
                                .title("YOU")
                        );

                    }
                });



                mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
                    @Nullable
                    @Override
                    public View getInfoWindow(@NonNull Marker marker) {

                        // The info window layout is created dynamically, parent is the info window
                        // container
                        LinearLayout parent = new LinearLayout(getApplicationContext());
                        //parent.setLayoutParams(new LinearLayout.LayoutParams(
                        //        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        //parent.setOrientation(LinearLayout.VERTICAL);

                        Intent intent = new Intent(mainActivity.this, map_displayPicture.class);
                        intent.putExtra("id",marker.getTitle());
                        startActivity(intent);


                        return parent;
                    }
                });

            }
        });


    }

//        mSeeStory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                function see Stories
//                Intent modifyActivity = new Intent(mainActivity.this, modifyFragment.class);
//                startActivity(modifyActivity);
//            }
//        });



        @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}

