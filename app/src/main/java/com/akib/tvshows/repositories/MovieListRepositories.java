package com.akib.tvshows.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.akib.tvshows.models.Movie;
import com.akib.tvshows.network.ApiClient;
import com.akib.tvshows.network.ApiService;
import com.akib.tvshows.responses.MovieListResponse;
import com.akib.tvshows.responses.ShowListResponses;
import com.akib.tvshows.responses.TrendingResponse;
import com.akib.tvshows.utils.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListRepositories {
    private static final String TAG = "MovieListRepositories";
    private final ApiService service;

    public MovieListRepositories() {
        service = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<? extends ShowListResponses> getPopularMovies(int page){
        MutableLiveData<MovieListResponse> data = new MutableLiveData<>();

        service.makeCallToAPIGetPopularMovies(Credentials.API_KEY,page).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                if (response.code()==200){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<? extends ShowListResponses> getTopRatedMovies(int page){
        MutableLiveData<MovieListResponse> data = new MutableLiveData<>();

        service.makeCallToAPIGetTopRatedMovies(
                Credentials.API_KEY,
                page).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                if (response.code()==200){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<ShowListResponses> getUpcomingMovies(int page){
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetUpcomingMovies(
                Credentials.API_KEY,
                page).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                if (response.code()==200){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<ShowListResponses> getNowPlayingMovieList(int page){
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetNowPlayingMovies(
                Credentials.API_KEY,
                page).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                if (response.code()==200){
                    data.setValue(response.body());
                }else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<ShowListResponses> getTrending(String time, int page){
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetTrending(
                "movie",
                time,
                Credentials.API_KEY,
                page).enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrendingResponse> call, @NonNull Response<TrendingResponse> response) {

                data.setValue(response.body());
                Log.d(TAG, "onResponse: on trending" + response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TrendingResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.d(TAG, "onFailure: on trending");
            }
        });

        return data;
    }

    public LiveData<Movie> getMovieDetails(String movieId){
        MutableLiveData<Movie> data =  new MutableLiveData<>();

        service.callToAPIGetMovieDetails(movieId,Credentials.API_KEY, "videos,images,credits").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.body() !=null) {
                    if (response.isSuccessful() && response.code() == 200) {
                        Log.d(TAG, "onResponse: movie details" + response.body().toString());
                        data.setValue(response.body());
                    } else {
                        Log.d(TAG, "onResponse: getting response but the value is garbage" + response.body().toString());
                        data.setValue(null);
                    }
                }else {
                    Log.d(TAG, "onResponse: code= " + response.code() + " message= " + response.message());
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: not getting response at all");
                data.setValue(null);
            }
        });
        return data;
    }
}
