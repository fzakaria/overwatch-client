package com.github.sunghyuk.overwatch.model;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class Rank {

    private String image;

    private int rating;

    public Rank(String img, int rating) {
        this.image = img;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
