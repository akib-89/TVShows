package com.akib.tvshows.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.akib.tvshows.models.Movie;
import com.akib.tvshows.repositories.MovieListRepositories;
import com.akib.tvshows.repositories.TVShowsRepositories;
import com.akib.tvshows.responses.ShowListResponses;


//view model class that links the live data from the api calling repository class in to the data class
public class PopularTVShowsViewModel extends ViewModel {

    private final MovieListRepositories movieListRepositories;
    private final TVShowsRepositories tvRepositories;
    public PopularTVShowsViewModel() {
        tvRepositories = new TVShowsRepositories();
        movieListRepositories = new MovieListRepositories();
    }

    public LiveData<? extends ShowListResponses> getTrendingTVShows(String time, int page){
        return tvRepositories.getTrending(time,page);
    }

    public LiveData<? extends ShowListResponses> getTvShowsAiringToday(int page){
        return tvRepositories.getAiringTodayTVShows(page);
    }

    public LiveData<? extends ShowListResponses> getOnAirTVShows(int page){
        return tvRepositories.getOnAirTVShows(page);
    }

    public LiveData<? extends ShowListResponses> getTopRatedTVShows(int page){
        return tvRepositories.getTopRatedTVShows(page);
    }

    public LiveData<? extends ShowListResponses> getPopularTVShows(int page){
        return tvRepositories.getPopularTVShows(page);
    }


    public LiveData<? extends ShowListResponses> getPopularMovies(int page){
        return movieListRepositories.getPopularMovies(page);
    }

    public LiveData<? extends ShowListResponses> getTopRatedMovies(int page){
        return movieListRepositories.getTopRatedMovies(page);
    }
    public LiveData<ShowListResponses> getUpcomingMovies(int page){
        return movieListRepositories.getUpcomingMovies(page);
    }
    public LiveData<ShowListResponses> getNowPlayingMovies(int page){
        return movieListRepositories.getNowPlayingMovieList(page);
    }

    public LiveData<ShowListResponses> getTrendingMovies(String time, int page){
        return movieListRepositories.getTrending(time, page);
    }

    public LiveData<Movie> getSingleMovieDetails(String movieID){
        return movieListRepositories.getMovieDetails(movieID);
    }


}
