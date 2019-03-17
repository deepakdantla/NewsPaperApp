package com.example.deepak.newspaperapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }


        News currentNews = getItem(position);

        // Find the TextView in the list_item_layout.xml layout with the ID name
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.name);
        titleTextView.setText(currentNews.getName());

        // Find the TextView in the list_item_layout.xml layout with the ID author
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        authorTextView.setText(currentNews.getAuthor());

        // Find the TextView in the list_item_layout.xml layout with the ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        // Find the TextView in the list_item_layout.xml layout with the ID
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        return listItemView;
    }
}
