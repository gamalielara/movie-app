package com.example.movieapp.service

import com.example.movieapp.model.GetPopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String): Call<GetPopularMovies>
}