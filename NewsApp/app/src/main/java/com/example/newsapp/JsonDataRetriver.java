package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonDataRetriver {

    public static ArrayList<JsonDataManage> StringToJsonFormat(String jsonString){
        ArrayList<JsonDataManage> json = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for (int i=0; i< results.length(); i++){
                JSONObject particular_news = results.getJSONObject(i);
                String id = particular_news.getString("id");
                String Date = particular_news.getString("webPublicationDate");
                String WebUrl = particular_news.getString("webUrl");
                json.add(new JsonDataManage(id,Date,WebUrl));
            }

        } catch (JSONException e) {
//            e.printStackTrace();
            Log.v("ERROR",e.toString());
        }
        Log.v("JSON FILE",json.toString());
        Log.v("VERY",jsonString);
        return json;
    }
}
