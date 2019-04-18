package com.example.deepak.newspaperapp;

public class News {

    //Variable to store the title of the news source eg., some heading of the news
    private String title;

    //Variable to store the author of the news eg., Deepak
    private String author;

    //Variable to store the time news came
    private String time;

    //Variable to store the address of the image
    private String imageUrl;

    //variable to store the web address of the news
    private String newsUrl;


    /*
     * This is a public constructor to create a new object for the News class
     * constructor takes five parameters
     * The parameters are name, author, title, description, time
     */
    public News(String title, String author,String time, String imageUrl,String newsUrl) {
        this.title = title;
        this.author = author;
        this.time = time;
        this.imageUrl = imageUrl;
        this.newsUrl = newsUrl;
    }


    //public method to get the name of the news
    public String getTitle() {
        return title;
    }

    //public method to get the author of the news
    public String getAuthor() {
        return author;
    }

    //public method to get the time of the news
    public String getTime() {
        return time;
    }

    //public method to get the address of the news image
    public String getImageUrl() {
        return imageUrl;
    }

    //public method to get the address of the news
    public String getNewsUrl() {
        return newsUrl;
    }
}
