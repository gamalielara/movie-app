package com.example.movieapp.service

import com.example.movieapp.model.GetNowPlayingMovies
import com.example.movieapp.model.GetPopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String): Call<GetPopularMovies>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey:String):Call<GetNowPlayingMovies>
}