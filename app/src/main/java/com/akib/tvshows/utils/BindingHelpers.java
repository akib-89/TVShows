package com.akib.tvshows.utils;

import com.akib.tvshows.models.Genre;
import com.akib.tvshows.models.Movie;
import com.akib.tvshows.models.TVShow;

public class BindingHelpers {
    public static String selectImageURL(TVShow tvShow, Movie movie) {
        if (movie != null) {
            return movie.getPoster();
        } else if (tvShow != null) {
            return tvShow.getPoster();
        }
        return null;

    }

    public static String selectTitle(TVShow tvShow, Movie movie) {
        if (movie != null) {
            return movie.getTitle();
        } else if (tvShow != null) {
            return tvShow.getTitle();
        }
        return "No movie Found";
    }

    public static String selectOverview(TVShow tvShow, Movie movie) {
        if (movie != null) {
            return movie.getOverview();
        } else if (tvShow != null) {
            return tvShow.getOverview();
        }
        return "No movie found";
    }

    public static String selectGenres(TVShow tvShow, Movie movie) {
        StringBuilder builder = new StringBuilder();
        if (movie != null) {
            for (Genre genre : movie.getGenres()) {
                builder.append(genre.name).append(" | ");
            }
        } else if (tvShow != null) {
            for (Genre genre : tvShow.getGenres()) {
                builder.append(genre.name).append(" | ");
            }
        }
        int last = builder.lastIndexOf(" | ");
        if (last > 0) {
            return builder.substring(0, last);
        } else {
            return builder.toString();
        }
    }
    public static String selectRating(TVShow tvShow, Movie movie) {
        if (movie != null) {
            return String.valueOf(movie.getRating());
        } else if (tvShow != null) {
            return String.valueOf(tvShow.getRating());
        }
        return "error";
    }
}
