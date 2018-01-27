package com.dev.sachin.sachinkumardemo.HelperClasses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sachin on 27-01-2018.
 */

public class ReposDetails {
    @SerializedName("name")
    String name;

    @SerializedName("html_url")
    String html_url;

    @SerializedName("description")
    String description;

    public String getName() {
        return name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }
}
