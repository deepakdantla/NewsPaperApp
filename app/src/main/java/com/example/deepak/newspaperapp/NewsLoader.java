package com.example.deepak.newspaperapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class NewsLoader extends AsyncTaskLoader<News> {

    public NewsLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public News loadInBackground() {
        return null;
    }
}
