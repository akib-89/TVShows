package com.akib.tvshows.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShow extends Shows{

    public String type;
    public List<String> languages;
    public List<Season> seasons;
    @SerializedName("in_production")
    public boolean inProduction;
    @SerializedName("episode_run_time")
    public List<Integer> episodeRunTime;
    @SerializedName("first_air_date")
    public String firstAirDate;
    @SerializedName("last_air_date")
    public String lastAirDate;
    @SerializedName("last_episode_to_air")
    public Episode lastEpisode;
    @SerializedName("next_episode_to_air")
    public Episode nextEpisode;
    @SerializedName("number_of_episodes")
    public int numEpisode;
    @SerializedName("number_of_seasons")
    public int numSeasons;
    @SerializedName("origin_country")
    public List<String> originCountry;
}
