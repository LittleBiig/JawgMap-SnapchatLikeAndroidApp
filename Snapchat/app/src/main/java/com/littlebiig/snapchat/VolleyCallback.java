package com.littlebiig.snapchat;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;

import java.util.List;

/**
 * Created by trunk on 24/11/2017.
 */

public interface VolleyCallback{
    void onSuccess(List<MarkerOptions> result);
}