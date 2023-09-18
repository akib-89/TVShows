package com.akib.tvshows.responses;

import com.akib.tvshows.models.Media;
import com.akib.tvshows.models.Shows;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrendingResponse extends ShowListResponses {
    @SerializedName("results")
    List<Media> medias;

    @Override
    public List<? extends Shows> getShows() {
        return medias;
    }
}
