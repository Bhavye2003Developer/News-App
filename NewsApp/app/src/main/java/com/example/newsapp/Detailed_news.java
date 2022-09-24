package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;

public class Detailed_news extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);
        detailed_info_task task = new detailed_info_task();
        task.execute();
    }
    private class detailed_info_task extends AsyncTask<URL, Integer, StringBuilder> {

        @Override
        protected void onPreExecute (){
        }

        @Override
        protected StringBuilder doInBackground(URL... urls) {
            Intent getwebdataIntent = getIntent();
            String get_web_url = getwebdataIntent.getStringExtra("DETAILED_KEY");
//            getURLData getwebdata = new getURLData(get_web_url);
            url_parse_data parsing = new url_parse_data(get_web_url);
            return parsing.getDetailedInfo();
        }

        @Override
        protected void onPostExecute(StringBuilder webdata) {
            ProgressBar progressBar = findViewById(R.id.indeterminateBar);
            progressBar.setVisibility(View.INVISIBLE);

            super.onPostExecute(webdata);
            TextView textView = findViewById(R.id.textView2);
            textView.setText(webdata);
//            Log.v("DETAILED",webdata);
        }
    }
}