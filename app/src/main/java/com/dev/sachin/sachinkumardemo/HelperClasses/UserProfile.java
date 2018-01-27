package com.dev.sachin.sachinkumardemo.HelperClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sachin on 27-01-2018.
 */

public class UserProfile {

    @SerializedName("avatar_url")
    String avatarImg;

    @SerializedName("html_url")
    String html_url;

    @SerializedName("followers_url")
    String followers_url;

    @SerializedName("repos_url")
    String repos_url;

    @SerializedName("following_url")
    String following_url;

    @SerializedName("location")
    String location;

    @SerializedName("name")
    String name;

    @SerializedName("login")
    String login;

    @SerializedName("public_repos")
    String publicRepos;

    @SerializedName("followers")
    String followers;

    @SerializedName("following")
    String following;

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
