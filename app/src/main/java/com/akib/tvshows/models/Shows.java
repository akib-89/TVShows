package com.akib.tvshows.models;


import androidx.annotation.NonNull;

import com.akib.tvshows.responses.VideoResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public abstract class Shows implements Serializable {
    private String id;
    private String homepage;
    private String overview;
    private String status;
    private String tagline;
    @SerializedName("videos")
    private VideoResponse videoResponse;
    private Images images;
    private Credits credits;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    private double rating;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("genres")
    private List<Genre> genres;
    // name for series and title for movies

    @SerializedName(value = "name", alternate = "title")
    private String title;


    @SerializedName(value = "original_name", alternate = "original_title")
    private String originalTitle;



    @NonNull
    @Override
    public String toString() {
        return  "id='" + getId() + '\'' +
                ", homepage='" + getHomepage() + '\'' +
                ", overview='" + getOverview() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", tagline='" + getTagline() + '\'' +
                ", backdrop='" + getBackdrop() + '\'' +
                ", rating=" + getRating() +
                ", originalLanguage='" + getOriginalLanguage() + '\'' +
                ", poster='" + getPoster() + '\'' +
                ", title='" + getTitle() +
                ", originalTitle=" + getOriginalTitle() +
                ", Images= " + images +
                ", VideoResponse= " + videoResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public VideoResponse getVideos() {
        return videoResponse;
    }

    public void setVideos(VideoResponse videos) {
        this.videoResponse = videos;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
}
