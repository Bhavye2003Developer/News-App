package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<JsonDataManage> {
    public CustomAdapter(@NonNull Context context, ArrayList<JsonDataManage> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_setting_json_data, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        JsonDataManage currentNumberPosition = getItem(position);
        TextView textView1 = currentItemView.findViewById(R.id.newsID);
        textView1.setText(currentNumberPosition.getId());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.Date);
        textView2.setText(currentNumberPosition.getDate());

        ConstraintLayout constraintLayout = currentItemView.findViewById(R.id.news_list);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), currentNumberPosition.getId(), Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getContext(),Detailed_news.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //HAD TO ADD THIS LINE FOR STARTING ACTIVITY FORM CUSTOM ADAPTER
                intent2.putExtra("DETAILED_KEY",currentNumberPosition.getWeb_Url());
                getContext().startActivity(intent2);
            }
        });
        // then return the recyclable view
        return currentItemView;
    }
}