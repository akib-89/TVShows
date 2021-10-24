package com.akib.tvshows.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Movie extends Shows {


    public long budget;
    public long revenue;
    public double runtime;
    @SerializedName("release_date")
    public String releaseDate;
    

    @NonNull
    @Override
    public String toString() {
        return  super.toString() +
                ", budget=" + budget +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
