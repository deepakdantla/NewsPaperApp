package com.example.deepak.newspaperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String NEWS_QUERY_URL = "https://newsapi.org/v2/top-headlines" +
            "?country=in&pageSize=5&apiKey=dd41c04600a844beb347724c6caa8495";

    private int NEWS_LOADER_ID = 1;
    private NewsAdapter newsAdapter;
    private TextView emptyView;
    private ProgressBar progressBar;
    private ImageView noInternet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView)findViewById(R.id.list_view);
        ArrayList<News> list = new ArrayList<>();
        list.add(new News("The Times of India","Murari Shetye"
                ,"2019-03-17T05:33:00Z"
                ,"https://img.etimg.com/thumb/msid-68446743,width-1070,height-580,imgsize-28657,overlay-economictimes/photo.jpg" ));

        list.add(new News("The Times of India","Murari Shetye"
                ,"2019-03-17T05:33:00Z"
                ,"https://img.etimg.com/thumb/msid-68446743,width-1070,height-580,imgsize-28657,overlay-economictimes/photo.jpg" ));

        list.add(new News("The Times of India","Murari Shetye"
                ,"2019-03-17T05:33:00Z"
                ,"https://img.etimg.com/thumb/msid-68446743,width-1070,height-580,imgsize-28657,overlay-economictimes/photo.jpg" ));
        NewsAdapter adapter = new NewsAdapter(this,R.layout.list_item_layout,list);

        listView.setAdapter(adapter);
    }
}
