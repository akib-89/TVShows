package com.akib.tvshows.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {
    public List<Logo> logos;
    public List<Poster> posters;
    @SerializedName("backdrops")
    public List<BackDrop> backDrops;

    @Override
    public String toString() {
        return "Images{" +
                "logos=" + logos.size() +
                ", posters=" + posters.size() +
                ", backDrops=" + backDrops.size() +
                '}';
    }
}
