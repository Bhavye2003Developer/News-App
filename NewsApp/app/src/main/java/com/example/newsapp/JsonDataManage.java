package com.example.newsapp;

public class JsonDataManage {
    private String Id, Date, Web_Url;
    public JsonDataManage(String Id, String Date, String Web_Url){
        this.Id = Id;
        this.Date = Date;
        this.Web_Url = Web_Url;
    }

    public String getId() {
        return Id;
    }
    public String getDate() {
        return Date;
    }
    public String getWeb_Url() {
        return Web_Url;
    }
}
