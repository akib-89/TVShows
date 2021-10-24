package com.akib.tvshows.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.akib.tvshows.network.ApiClient;
import com.akib.tvshows.network.ApiService;
import com.akib.tvshows.responses.ShowListResponses;
import com.akib.tvshows.responses.TVListResponse;
import com.akib.tvshows.responses.TrendingResponse;
import com.akib.tvshows.utils.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//this is the repository class that links the network call data to the view model classes
public class TVShowsRepositories {

    private static final String TAG = "PopularTVShowsRepo";
    private final ApiService service;

    public TVShowsRepositories() {
        service = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<ShowListResponses> getAiringTodayTVShows(int page) {
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetTVShowsAiringToday(
                Credentials.API_KEY,
                page
        ).enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVListResponse> call, @NonNull Response<TVListResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TVListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ShowListResponses> getOnAirTVShows(int page) {
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetOnAirTVShows(
                Credentials.API_KEY,
                page
        ).enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVListResponse> call, @NonNull Response<TVListResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TVListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ShowListResponses> getTopRatedTVShows(int page) {
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetTopRatedTVShows(
                Credentials.API_KEY,
                page
        ).enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVListResponse> call, @NonNull Response<TVListResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }
            @Override
            public void onFailure(@NonNull Call<TVListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ShowListResponses> getPopularTVShows(int page) {
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetPopularTVShows(
                Credentials.API_KEY,
                page
        ).enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVListResponse> call, @NonNull Response<TVListResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TVListResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ShowListResponses> getTrending(String time, int page){
        MutableLiveData<ShowListResponses> data = new MutableLiveData<>();

        service.makeCallToAPIGetTrending(
                "tv",
                time,
                Credentials.API_KEY,
                page).enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrendingResponse> call, @NonNull Response<TrendingResponse> response) {
                data.setValue(response.body());
                Log.d(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(@NonNull Call<TrendingResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
