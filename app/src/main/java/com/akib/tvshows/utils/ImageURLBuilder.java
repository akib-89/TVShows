package com.akib.tvshows.utils;

public class ImageURLBuilder {
    private static final ImageURLBuilder builder = new ImageURLBuilder();
    private String baseImageUrl;

    private ImageURLBuilder() {
        baseImageUrl = "https://image.tmdb.org/t/p/";
    }

    public static ImageURLBuilder getBuilder() {
        return builder;
    }

    public String getFullImageUrl(String url, String width) {
        return baseImageUrl + width + "/" + url;
    }



}