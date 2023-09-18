package com.akib.tvshows.responses;

import com.akib.tvshows.models.Shows;
import com.akib.tvshows.models.TVShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVListResponse extends ShowListResponses {
    @SerializedName("results")
    private List<TVShow> shows;

    @Override
    public List<? extends Shows> getShows() {
        return shows;
    }
}
