package com.example.roadrescue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    private HashMap<String, String> getSingleNearbyPlace(JSONObject googlePlaceJSON) {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String NameOfPlace = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitube = "";
        String reference = "";

        try {
            if (!googlePlaceJSON.isNull("name")) {
                NameOfPlace = googlePlaceJSON.getString("name");
            }
            if (!googlePlaceJSON.isNull("vicinity")) {
                vicinity = googlePlaceJSON.getString("vicinity");
            }
            latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitube = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJSON.getString("reference");


            googlePlaceMap.put("place_name", NameOfPlace);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("Lat", latitude);
            googlePlaceMap.put("lng", longitube);
            googlePlaceMap.put("reference", reference);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }


    private List<HashMap<String,String>>getAllNearbyPlaces(JSONArray jsonArray)
    {
        int counter = jsonArray.length();
        List<HashMap<String,String>>NearbyPlaceList = new ArrayList<>();
        HashMap<String, String>NearbyPlaceMap=null;
        for(int i=0; i<counter; i++)
        {
            try {
                NearbyPlaceMap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
                NearbyPlaceList.add(NearbyPlaceMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  NearbyPlaceList;
    }

    public List<HashMap<String,String >> parse(String jSONdata){
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jSONdata);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getAllNearbyPlaces(jsonArray);

    }




}
