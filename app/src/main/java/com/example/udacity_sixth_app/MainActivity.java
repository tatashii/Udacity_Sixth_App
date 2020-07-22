package com.example.udacity_sixth_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<NewsClass>> {

    private static final int GUARDIAN_LOADER_ID = 1;
    ListView listView;
    private TextView mEmptyStateTextView;
    ProgressBar progressBar;
    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<NewsClass> newslist = new ArrayList<NewsClass>();
        progressBar = findViewById(R.id.loading_indicator);
        listView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);
        mNewsAdapter = new NewsAdapter(this, newslist);
        listView.setAdapter(mNewsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                NewsClass newsClass = mNewsAdapter.getItem(position);
                Uri uri = Uri.parse(newsClass.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getSupportLoaderManager().initLoader(GUARDIAN_LOADER_ID, null, this);
        } else {
            progressBar = findViewById(R.id.loading_indicator);
            progressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_news);
        }
    }

    @Override
    public Loader<List<NewsClass>> onCreateLoader(int i, Bundle bundle) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("q", "debates")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("api-key", "test");
        String myUrl = builder.build().toString();

        return new NewsLoader(this, myUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsClass>> loader, List<NewsClass> news) {

        mEmptyStateTextView.setText(R.string.no_news);
        progressBar = findViewById(R.id.loading_indicator);
        progressBar.setVisibility(View.GONE);
        mNewsAdapter.clear();
        if (news != null && !news.isEmpty()) {
            mNewsAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsClass>> loader) {
        mNewsAdapter.clear();
    }
}