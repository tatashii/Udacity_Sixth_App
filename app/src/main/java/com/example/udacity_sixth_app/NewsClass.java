package com.example.udacity_sixth_app;

public class NewsClass {
    private String mtitle;
    private String msection;
    private String mdate;
    private String mUrl;
    private String mauthor;

    public NewsClass(String title, String section, String date, String url, String author) {
        mtitle = title;
        msection = section;
        mdate = date;
        mUrl = url;
        mauthor = author; }

    public String getMtitle() {
        return mtitle;
    }

    public String getMsection() {
        return msection;
    }

    public String getMdate() {
        return mdate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getMauthor() {
        return mauthor;
    }
}
