package com.github.sunghyuk.overwatch.model;

/**
 * Created by sunghyuk on 2016. 7. 25..
 */
public class Achievement {

    private String name;

    private String icon;

    private String description;

    private boolean achieved;

    public Achievement(String name, String icon, String description, boolean achieved) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.achieved = achieved;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAchieved() {
        return achieved;
    }
}
