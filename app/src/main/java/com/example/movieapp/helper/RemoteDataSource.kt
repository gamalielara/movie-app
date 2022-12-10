package com.example.movieapp.helper

import com.example.movieapp.service.ApiKey
import com.example.movieapp.service.ApiService

class RemoteDataSource (private val apiService: ApiService) {

    suspend fun getNowPlayingMovies() = apiService.getNowPlayingMovies(ApiKey.API_KEY)

    suspend fun getPopularMovies() = apiService.getPopularMovies(ApiKey.API_KEY)

    suspend fun getTopRatedMovies() = apiService.getTopRatedMovies(ApiKey.API_KEY)

}

