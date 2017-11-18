package com.littlebiig.snapchat;



import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
        import android.support.v4.app.Fragment;
import android.os.Bundle;
        import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.LinearLayout;

        import com.mapbox.mapboxsdk.Mapbox;
        import com.mapbox.mapboxsdk.annotations.Marker;
        import com.mapbox.mapboxsdk.annotations.MarkerOptions;
        import com.mapbox.mapboxsdk.geometry.LatLng;
        import com.mapbox.mapboxsdk.maps.MapView;
        import com.mapbox.mapboxsdk.maps.MapboxMap;
        import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Created by trunk on 25/10/2017.
 */

public class map_mapFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    private MapView mapView;
    private LatLng mLatLng;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final map_getLocation getLoca= new map_getLocation(getActivity().getApplicationContext(),getActivity());



        // Here, thisActivity is the current activity

        Mapbox.getInstance(getActivity(), "pk.eyJ1IjoicXVlbnRpbmRlYm91dCIsImEiOiJjajh6em13ZGkyZXQ3MzJwMjB3d2RpdDJxIn0.JNUGKO5NH1gMfVAXRIu2PQ");
        getActivity().setContentView(R.layout.fragment_map);
        mapView =  getView().findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


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
                        LinearLayout parent = new LinearLayout(getActivity().getApplicationContext());
                        //parent.setLayoutParams(new LinearLayout.LayoutParams(
                        //        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        //parent.setOrientation(LinearLayout.VERTICAL);

                        Intent intent = new Intent(getActivity(),map_displayPicture.class);
                        intent.putExtra("id",marker.getTitle());
                        startActivity(intent);


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


}

