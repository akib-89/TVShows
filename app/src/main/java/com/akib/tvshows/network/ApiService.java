package com.akib.tvshows.network;

import com.akib.tvshows.models.Movie;
import com.akib.tvshows.responses.MovieListResponse;
import com.akib.tvshows.responses.TVListResponse;
import com.akib.tvshows.responses.TrendingResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

//this is the interface where all the network calls are declared and this is called by the repository class of the app
public interface ApiService {

    @GET("movie/popular")
    Call<MovieListResponse> makeCallToAPIGetPopularMovies(
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("movie/upcoming")
    Call<MovieListResponse> makeCallToAPIGetUpcomingMovies(
            @Header("Authorization")String key,
            @Query("page") int page
    );

    @GET("movie/top_rated")
    Call<MovieListResponse> makeCallToAPIGetTopRatedMovies(
            @Header("Authorization")String key,
            @Query("page") int page
    );

    @GET("movie/now_playing")
    Call<MovieListResponse> makeCallToAPIGetNowPlayingMovies(
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("trending/{media_type}/{time_window}")
    Call<TrendingResponse> makeCallToAPIGetTrending(
            @Path("media_type") String mediaType,
            @Path("time_window") String time,
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("tv/airing_today")
    Call<TVListResponse> makeCallToAPIGetTVShowsAiringToday(
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("tv/on_the_air")
    Call<TVListResponse> makeCallToAPIGetOnAirTVShows(
            @Header("Authorization") String key,
            @Query("page") int page
    );


    @GET("tv/popular")
    Call<TVListResponse> makeCallToAPIGetPopularTVShows(
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("tv/top_rated")
    Call<TVListResponse> makeCallToAPIGetTopRatedTVShows(
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("movie/popular")
    Response<MovieListResponse> callToAPIForPopularMovies(
            @Header("Authorization") String key,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Call<Movie> callToAPIGetMovieDetails(
            @Path("movie_id") String id,
            @Header("Authorization") String key,
            @Query("append_to_response") String values
    );
}
