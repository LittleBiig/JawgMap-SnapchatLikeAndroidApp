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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by trunk on 25/10/2017.
 */

public class map_mapFragment extends Fragment {
    private MapView mapView;
    private LatLng mLatLng;
    private static final double DISTANCE_BETWEEN = 5000;

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

                getLoca.locationManager.requestLocationUpdates(getLoca.bestLocationProvider, 20000, 10,getLoca.locationListener=new LocationListener() {

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
                        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                .target(mLatLng)
                                .zoom(11)
                                .build());
                        requestPicture(mLatLng,new VolleyCallback(){
                            @Override
                            public void onSuccess(List<MarkerOptions> result){
                                ListIterator<MarkerOptions> li=result.listIterator();
                                while (li.hasNext()) {
                                    mapboxMap.addMarker(li.next());
                                }
                            }
                        });



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
                        if(!TextUtils.equals(marker.getTitle(),"YOU")) {
                            Intent intent = new Intent(getActivity(), map_displayPicture.class);
                            intent.putExtra("id", marker.getTitle());
                            startActivity(intent);
                        }

                        return parent;
                    }
                });

            }
        });

    }

    private void requestPicture(final LatLng mLatLng,final VolleyCallback callback) {



        String url = "https://snapchat-5f9c8.firebaseio.com/files.json";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array=response.getJSONArray("pictures");
                            List<MarkerOptions> Markers = new ArrayList<MarkerOptions>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject c = array.getJSONObject(i);
                                String lat = c.getString("Lat");
                                String lng = c.getString("Lng");
                                String name = c.getString("Name");

                                LatLng latLng = new LatLng( Double.parseDouble(lat), Double.parseDouble(lng));
                                float[] result=new float[3];
                                Location.distanceBetween(latLng.getLatitude(),latLng.getLongitude(),mLatLng.getLatitude(),mLatLng.getLongitude(),result);
                                if(result[0]<DISTANCE_BETWEEN) {
                                    Markers.add(new MarkerOptions()
                                            .position(new LatLng(latLng.getLatitude(), latLng.getLongitude()))
                                            .title(name));
                                }
                            }
                            Markers.add(new MarkerOptions()
                                    .position(new LatLng(mLatLng.getLatitude(), mLatLng.getLongitude()))
                                    .title("YOU"));
                            callback.onSuccess(Markers);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsObjRequest);

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

