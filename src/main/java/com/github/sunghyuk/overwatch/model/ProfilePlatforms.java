package com.github.sunghyuk.overwatch.model;

import com.github.sunghyuk.overwatch.model.ProfilePlatform;

import java.util.List;

/**
 * Created by sunghyuk on 2016. 7. 21..
 */
public class ProfilePlatforms {

    private String id;

    private List<ProfilePlatform> platforms;

    public ProfilePlatforms(String id, List<ProfilePlatform> platforms) {
        this.id = id;
        this.platforms = platforms;
    }

    public String getId() {
        return id;
    }

    public List<ProfilePlatform> getPlatforms() {
        return platforms;
    }

}
