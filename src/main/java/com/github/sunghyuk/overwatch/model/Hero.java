package com.github.sunghyuk.overwatch.model;

/**
 * Created by sunghyuk on 2016. 7. 22..
 */
public class Hero {

    private String name;

    private String image;

    public Hero(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

}
