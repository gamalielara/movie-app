package com.example.movieapp.service

import com.example.movieapp.model.GetNowPlayingMovies
import com.example.movieapp.model.GetPopularMovies
import com.example.movieapp.model.GetTopRatedMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String): GetPopularMovies

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey:String):GetNowPlayingMovies

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): GetTopRatedMovies
}