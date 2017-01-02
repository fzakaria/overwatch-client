package com.github.sunghyuk.overwatch.model;

import java.util.Objects;

/**
 * Created by sunghyuk on 2016. 7. 19..
 */
public class ProfilePlatform {

    private String platform;
    private boolean hasAccount;
    private String careerLink;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isHasAccount() {
        return hasAccount;
    }

    public void setHasAccount(boolean hasAccount) {
        this.hasAccount = hasAccount;
    }

    public String getCareerLink() {
        return careerLink;
    }

    public void setCareerLink(String careerLink) {
        this.careerLink = careerLink;
    }
}
