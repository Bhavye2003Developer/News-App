package com.example.newsapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class getURLData {
    private String url_string;
    public getURLData(String URL_string){
        this.url_string = URL_string;
    }

    public String getJSONStringURL() {
        URL url;
        {
            try {
                url = new URL(url_string); //CREATING URL OBJECT
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //CREATING HTTPCONNECTION
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //GETTING JSON STRING
                    String JSONResponse = getJSONSTRINGResponse(httpURLConnection);
//                    Log.v("JSON STRING", JSONResponse);
                    return JSONResponse;
                }
            } catch (MalformedURLException e) {
//                e.printStackTrace();
                Log.v("ERROR",e.toString());
            } catch (IOException e) {
//                e.printStackTrace();
                Log.v("ERROR",e.toString());
            }
        }
        return null;
    }

    String getJSONSTRINGResponse(HttpURLConnection httpURLConnection){
        InputStream inputStream = null;
        String JSONString = "";
        try {
            inputStream = httpURLConnection.getInputStream();
            JSONString = getJSONSTRING(inputStream);
        } catch (IOException e) {
//            e.printStackTrace();
            Log.v("ERROR",e.toString());
        }
        return JSONString;
    }

    String getJSONSTRING(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
//                e.printStackTrace();
                Log.v("ERROR",e.toString());
            }
            while (line != null) {
                stringBuilder.append(line);
                try {
                    line = reader.readLine();
                } catch (IOException e) {
//                    e.printStackTrace();
                    Log.v("ERROR",e.toString());
                }
            }
        }
        return stringBuilder.toString();
    }
}
