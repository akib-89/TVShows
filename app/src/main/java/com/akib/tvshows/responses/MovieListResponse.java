package com.akib.tvshows.responses;

import com.akib.tvshows.models.Movie;
import com.akib.tvshows.models.Shows;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse extends ShowListResponses {
    @SerializedName("results")
    private List<Movie> shows;

    @Override
    public List<? extends Shows> getShows() {
        return shows;
    }
}
