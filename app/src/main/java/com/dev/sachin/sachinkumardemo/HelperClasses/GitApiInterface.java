package com.dev.sachin.sachinkumardemo.HelperClasses;

import android.preference.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by sachin on 27-01-2018.
 */

public interface GitApiInterface {

   /*@GET("users/sachinkhalu123")
    Call<UserProfile> getProfile();
*/


    @GET("https://api.github.com/user")
    Call<UserProfile> getProfile(@Header("Authorization") String authkey);

    @GET
    Call<List<ReposDetails>> getRepos(@Url() String url);



}
