package com.example.deepak.newspaperapp;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static String NEWS_QUERY_URL = "https://newsapi.org/v2/top-headlines" +
            "?country=in&apiKey=dd41c04600a844beb347724c6caa8495";

    private int NEWS_LOADER_ID = 1;
    private NewsAdapter newsAdapter;
    private TextView emptyView;
    private ProgressBar progressBar;
    private ImageView noInternet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*creating the object of connectivity manager which helps in getting info
          about the network connection  */
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //networkinfo stores the info about the network status
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //find the empty view in the layout
        emptyView = findViewById(R.id.empty_view);

        ListView listView = findViewById(R.id.list_view);
        listView.setEmptyView(emptyView);
        progressBar = findViewById(R.id.pbar);
        noInternet = findViewById(R.id.no_internet);
        noInternet.setVisibility(View.GONE);
        if (networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID,null,this);
        }else {
            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }
        newsAdapter = new NewsAdapter(MainActivity.this, new ArrayList<News>());
        listView.setAdapter(newsAdapter);
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsAdapter.getItem(position);
                Uri uri = Uri.parse(news.getNewsUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(this,NEWS_QUERY_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> articlesObtained) {

        emptyView.setText("No Articles Found");
        newsAdapter.clear();
        if (articlesObtained != null && !articlesObtained.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.GONE);
            if (articlesObtained != null && !articlesObtained.isEmpty()) {
                newsAdapter.addAll(articlesObtained);
            }

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }
}
