package com.akib.tvshows.network;

import com.akib.tvshows.utils.Credentials;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//this is the single tone retrofit class to call the API
public class ApiClient {

//    private static final String BASE_URL = "https://www.episodate.com/api/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
