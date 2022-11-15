package com.example.movieapp.service

import com.example.movieapp.model.GetNowPlayingMovies
import com.example.movieapp.model.GetPopularMovies
import com.example.movieapp.model.GetTopRatedMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String): Call<GetPopularMovies>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey:String):Call<GetNowPlayingMovies>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<GetTopRatedMovies>
}