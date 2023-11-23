package com.example.roadrescue;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object, String, String> {


    private String googleplaceData, url;
    private GoogleMap mMap;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleplaceData = downloadUrl.ReadTheURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("API Response", s);

        List<HashMap<String, String>> nearByPlacesList = null;
        DataParser dataParser= new DataParser();
        nearByPlacesList = dataParser.parse(s);
        Log.d("MarkerDebug", "Nearby Places List Size: " + nearByPlacesList.size());
        DisplayNearbyPlaces(nearByPlacesList);
    }
    private void DisplayNearbyPlaces(List<HashMap<String, String>> nearByPlacesList){
        for (int i=0; i<nearByPlacesList.size(); i++){



            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlenearByPlace = nearByPlacesList.get(i);
            String nameOfPlace = googlenearByPlace.get("place_name");
            String vicinity = googlenearByPlace.get("vicinity");

            // Check if lat and lng are not null before parsing them
            String latStr = googlenearByPlace.get("Lat");
            String lngStr = googlenearByPlace.get("lng");
            if (latStr != null && lngStr != null) {
                double lat = Double.parseDouble(latStr);
                double lng = Double.parseDouble(lngStr);

                LatLng latLng = new LatLng(lat, lng);

                markerOptions.position(latLng);
                markerOptions.title(nameOfPlace + ":" + vicinity);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                Log.d("MarkerDebug", "Name of Place: " + nameOfPlace);
                Log.d("MarkerDebug", "Latitude: " + lat);
                Log.d("MarkerDebug", "Longitude: " + lng);
            } else {
                Log.e("MarkerDebug", "Latitude or Longitude is null for place " + nameOfPlace);
            }

        }
    }
}
