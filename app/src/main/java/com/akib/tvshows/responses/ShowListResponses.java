package com.akib.tvshows.responses;

import androidx.annotation.NonNull;

import com.akib.tvshows.models.Shows;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class ShowListResponses {
    private int page;
    @SerializedName("total_pages")
    private int totalPage;
    @SerializedName("total_results")
    private int totalResults;


    public int getPage() {
        return page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public abstract List<? extends Shows> getShows();

    @NonNull
    @Override
    public String toString() {
        return "ShowResponses{" +
                "page=" + page +
                ", totalPage=" + totalPage +
                ", totalResults=" + totalResults +
                '}';
    }
}
