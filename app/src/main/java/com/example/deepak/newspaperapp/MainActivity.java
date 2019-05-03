package com.example.deepak.newspaperapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    private String NEWS_QUERY_URL = "https://newsapi.org/v2/top-headlines?country=in&pageSize=100&apiKey=dd41c04600a844beb347724c6caa8495";

    private int NEWS_LOADER_ID = 1;
    private NewsAdapter newsAdapter;
    private TextView emptyView;
    private ProgressBar progressBar;
    private ImageView noInternet;
    private Spinner spinner;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_headlines);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        /*creating the object of connectivity manager which helps in getting info
          about the network connection  */
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //networkinfo stores the info about the network status
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //find the empty view in the layout
        emptyView = findViewById(R.id.empty_view);

        //spinner view
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>
                (
                        this
                        , android.R.layout.simple_spinner_item
                        , getResources().getStringArray(R.array.newsCategory)
                );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        ListView listView = findViewById(R.id.list_view);
        listView.setEmptyView(emptyView);
        progressBar = findViewById(R.id.pbar);
        noInternet = findViewById(R.id.no_internet);
        noInternet.setVisibility(View.GONE);
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
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
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = spinner.getSelectedItem().toString();
                Intent intent;
                if (text == "Select category") {
                    //
                } else {
                    switch (text) {
                        case "Business":
                            intent = new Intent(MainActivity.this, Business.class);
                            startActivity(intent);
                            break;
                        case "Sports":
                            intent = new Intent(MainActivity.this, Sports.class);
                            startActivity(intent);
                            break;
                        case "General":
                            intent = new Intent(MainActivity.this, General.class);
                            startActivity(intent);
                            break;
                        case "Health":
                            intent = new Intent(MainActivity.this, Health.class);
                            startActivity(intent);
                            break;
                        case "Technology":
                            intent = new Intent(MainActivity.this, Technology.class);
                            startActivity(intent);
                            break;
                        case "Science":
                            intent = new Intent(MainActivity.this, Science.class);
                            startActivity(intent);
                            break;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this
                        , "You can select news based on categories"
                        , Toast.LENGTH_SHORT).show();
            }
        });


        Intent intent = getIntent();
        if(intent != null && intent.getData()!= null){
            Toast.makeText(this, "Deep link success", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(this, NEWS_QUERY_URL);
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
        //do nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu_id = item.getItemId();
        String title = item.getTitle().toString();
        switch (menu_id) {
            case R.id.logOut:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                makeText(this, "Logged out successfully " + title, Toast.LENGTH_SHORT).show();
                // call this to finish the current activity
                finish();
                break;
        }
        return true;
    }

}


