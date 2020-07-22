package com.example.udacity_sixth_app;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsClass>> {
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsClass> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsClass> newsList = QueryUtils.fetchData(mUrl);
        return newsList;
    }
}