package com.example.newsapp;


import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class url_parse_data {
    private String url_string;

    public url_parse_data(String url_string) {
        this.url_string = url_string;
    }

    public StringBuilder getDetailedInfo() {
        try {
            URL url = new URL(url_string);
            URLConnection uc;
            try {
                uc = url.openConnection();
                InputStream is = uc.getInputStream();
                int i;
                StringBuilder sb = new StringBuilder();
                while ((i = is.read()) != -1) {
                    sb.append((char) i);
                }
                String source = sb.toString();
                Document doc = Jsoup.parse(source);
//                Document doc = Jsoup.parse(is,"ISO-8859-1",url_string);
                Element div = doc.getElementById("maincontent");
                StringBuilder stringBuilder = new StringBuilder();

                Elements title_element = doc.getElementsByTag("title");

                stringBuilder.append(title_element.text());
                stringBuilder.append("\n\n\n");

                Elements para = div.getElementsByTag("p");
                para.remove(para.last());
                int length_para = para.size();
//                System.out.println(length_para);

                for (int j = 0; j < length_para; j++) {
                    Element particular = para.get(j);
                    System.out.println(particular.text());
                    stringBuilder.append(para.text());
                    stringBuilder.append("\n\n");

//                    System.out.println("\n\n");
                    Log.v("STRING", stringBuilder.toString());


                    //Getting title
                    return stringBuilder;
                }

            } catch (IOException e) {
                Log.v("ERROR",e.toString());
            }

        } catch (MalformedURLException e) {
//            e.printStackTrace();
            Log.v("ERROR",e.toString());
        }
        return null;
    }
}