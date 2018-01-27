package com.dev.sachin.sachinkumardemo.HelperClasses;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sachin on 27-01-2018.
 */

public class GitApiClient {

    public static final String baseUrl="https://api.github.com/";
    public static Retrofit retrofit=null;
    public static Retrofit getApiClient(){

        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(baseUrl).
                    addConverterFactory(GsonConverterFactory.create()).build();
    }
    return retrofit;
    }
}
