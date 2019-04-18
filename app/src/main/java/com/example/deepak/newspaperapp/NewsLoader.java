package com.example.deepak.newspaperapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mURL;

    public NewsLoader(@NonNull Context context,String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mURL == null) return null;
        List<News> listArticles = QueryUtils.fetchArticleData(mURL);
        return listArticles;
    }
}
