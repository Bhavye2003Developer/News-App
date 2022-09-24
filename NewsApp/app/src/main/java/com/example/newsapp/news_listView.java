package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;


public class news_listView extends AppCompatActivity {
    SimpleCursorAdapter mAdapter;
    TextView textView2;
    String get_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list_view);

//        Intent get_search_word = getIntent();
//        get_word = get_search_word.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        Toast.makeText(this, get_word, Toast.LENGTH_SHORT).show(); // for checking
        // Prepare the AsyncTask.
        //start a new one.

        myTask new_task = new myTask();
        new_task.execute();
    }

    private class myTask extends AsyncTask<URL, Integer, ArrayList<JsonDataManage>> {
        protected void onPreExecute (){
        }

        @Override
        protected ArrayList<JsonDataManage> doInBackground(URL... urls) {
//            String URL_string = "https://content.guardianapis.com/search?q=%s&api-key=e283ed76-57f6-48d0-a20a-87be5220e241";
            Intent get_search_word = getIntent();
            get_word = get_search_word.getStringExtra(MainActivity.EXTRA_MESSAGE);
            String URL_string = String.format("https://content.guardianapis.com/search?q=%s&api-key=e283ed76-57f6-48d0-a20a-87be5220e241",get_word);
//            Log.v("MESSAGE",get_word);
//            Log.v("URL_VAL",URL_string);
//            Log.v("IMPORTANT","LOADING");
            getURLData getData = new getURLData(URL_string);
            JsonDataRetriver jsonDataRetriver = new JsonDataRetriver();
            ArrayList<JsonDataManage> jsonDataManages = jsonDataRetriver.StringToJsonFormat(getData.getJSONStringURL());
            return jsonDataManages;
        }

        @Override
        protected void onPostExecute(ArrayList<JsonDataManage> arrayList) {
            super.onPostExecute(arrayList);
            int number_of_elements = arrayList.size();
            CustomAdapter adapter = new CustomAdapter(news_listView.this,arrayList);
            ListView root_view = findViewById(R.id.root_view);

            TextView title = findViewById(R.id.title_string);
            title.setText("Number of searches : " + number_of_elements);
            ProgressBar progressBar = findViewById(R.id.indeterminateBar);
            progressBar.setVisibility(View.INVISIBLE);
            root_view.setAdapter(adapter);
        }
    }
}