package com.example.deepak.newspaperapp;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    Context context = getContext();

    public NewsAdapter(@NonNull Context context, @NonNull ArrayList<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }


        News currentNews = getItem(position);

        // Find the TextView in the list_item_layout.xml layout with the ID name
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(currentNews.getTitle());

        // Find the TextView in the list_item_layout.xml layout with the ID author
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        authorTextView.setText(currentNews.getAuthor());

        // Find the TextView in the list_item_layout.xml layout with the ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String date = currentNews.getTime();
        String[] dateParts = date.split("T");
        String dateModified = dateParts[0];
        timeTextView.setText(dateModified);

        // Find the TextView in the list_item_layout.xml layout with the ID
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
//        Uri imageUri = Uri.parse(currentNews.getNewsUrl());
//        imageView.setImageURI(imageUri);
            Picasso.get()
                    .load(currentNews.getImageUrl())
                    .placeholder(R.drawable.download)
                    .fit()
                    .centerCrop()
                    .into(imageView);

        return listItemView;
    }

}
