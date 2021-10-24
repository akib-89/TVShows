package com.akib.tvshows.models;

import com.google.gson.annotations.SerializedName;

public class Media extends Shows{
    @SerializedName("media_type")
    public String mediaType;
}
