package com.example.dara.yuknonton;

import com.example.dara.yuknonton.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbClient {

    //mengambil data yang sdg tayang
    @GET("/3/movie/now_playing")
    Call<MovieList> getNowPlayingMovies(@Query("api_key") String api_key);

    //data yang akan tayang
    @GET("/3/movie/upcoming")
    Call<MovieList> getUpcomingMovies(@Query("api_key") String api_key);



}
