package com.littlebiig.snapchat;



import android.Manifest;
        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.annotation.NonNull;
        import android.support.v4.app.Fragment;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.location.Criteria;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.location.LocationProvider;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

        import com.mapbox.mapboxsdk.Mapbox;
        import com.mapbox.mapboxsdk.annotations.Marker;
        import com.mapbox.mapboxsdk.annotations.MarkerOptions;
        import com.mapbox.mapboxsdk.geometry.LatLng;
        import com.mapbox.mapboxsdk.maps.MapView;
        import com.mapbox.mapboxsdk.maps.MapboxMap;
        import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

        import java.net.URL;
        import java.util.ArrayList;

        import static android.content.pm.PackageManager.PERMISSION_GRANTED;
        import static com.littlebiig.snapchat.getLocation.MY_PERMISSIONS_REQUEST_LOCATION;

/**
 * Created by trunk on 25/10/2017.
 */

public class mapFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    private MapView mapView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final getLocation getLoca= new getLocation(getActivity().getApplicationContext(),getActivity());
        getLoca.checkLocationPermission();



        // Here, thisActivity is the current activity

        Mapbox.getInstance(getActivity(), "pk.eyJ1IjoicXVlbnRpbmRlYm91dCIsImEiOiJjajh6em13ZGkyZXQ3MzJwMjB3d2RpdDJxIn0.JNUGKO5NH1gMfVAXRIu2PQ");
        getActivity().setContentView(R.layout.fragment_map);
        mapView =  getView().findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);




        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(0, 0))
                        .title("Spain")
                        );
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(70, 0))
                        .title("Egypt")
                        );
                mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
                    @Nullable
                    @Override
                    public View getInfoWindow(@NonNull Marker marker) {

                        // The info window layout is created dynamically, parent is the info window
                        // container
                        LinearLayout parent = new LinearLayout(getActivity().getApplicationContext());
                        //parent.setLayoutParams(new LinearLayout.LayoutParams(
                        //        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        //parent.setOrientation(LinearLayout.VERTICAL);

                        // Depending on the marker latitude, the correct image source is used. If you
                        // have many markers using different images, extending Marker and
                        // baseMarkerOptions, adding additional options such as the image, might be
                        // a better choice.
                        //ImageView countryFlagImage = new ImageView(getActivity().getApplicationContext());

                        if (TextUtils.equals(marker.getTitle(), getString(R.string.custom_window_marker_title_spain))) {

                            Intent intent = new Intent(getActivity(),displayPicture.class);
                            intent.putExtra("id","image");
                            startActivity(intent);

                        } else if (TextUtils.equals(marker.getTitle(), getString(R.string.custom_window_marker_title_egypt))) {
                            //countryFlagImage.setImageDrawable(ContextCompat.getDrawable(
                            //getActivity().getApplicationContext(), R.drawable.egypt));
                            Intent intent = new Intent(getActivity(),displayPicture.class);
                            intent.putExtra("id","image2");
                            startActivity(intent);
                        } else {
                            // By default all markers without a matching latitude will use the
                            // Germany flag
                            //countryFlagImage.setImageDrawable(ContextCompat.getDrawable(
                            //getActivity().getApplicationContext(), R.drawable.flag_of_germany));
                        }

                        // Set the size of the image
                        //countryFlagImage.setLayoutParams(new android.view.ViewGroup.LayoutParams(150, 100));

                        // add the image view to the parent layout
                        //parent.addView(countryFlagImage);

                        return parent;
                    }
                });

            }
        });





    }


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
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    /*@Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }*/

}

