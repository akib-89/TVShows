package com.akib.tvshows.responses;

import com.akib.tvshows.models.Video;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {
    @SerializedName("results")
    public List<Video> videos;
}
