package com.example.deepak.newspaperapp;

public class News {

    //Variable to store the name of the news source eg., The Times Of India
    private String name;

    //Variable to store the author of the news eg., Deepak
    private String author;

    //Variable to store the time news came
    private String time;

    //Variable to store the address of the image
    private String imageUrl;


    /*
     * This is a public constructor to create a new object for the News class
     * constructor takes five parameters
     * The parameters are name, author, title, description, time
     */
    public News(String name, String author, String time, String imageUrl) {
        this.name = name;
        this.author = author;
        this.time = time;
        this.imageUrl = imageUrl;
    }


    //public method to get the name of the news
    public String getName() {
        return name;
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
}
