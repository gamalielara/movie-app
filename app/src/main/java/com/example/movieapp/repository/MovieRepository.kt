package com.example.movieapp.repository

import com.example.movieapp.helper.RemoteDataSource

class MovieRepository (private val remoteDataSource: RemoteDataSource) {

    suspend fun getNowPlayingMovies() = remoteDataSource.getNowPlayingMovies()

    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()

    suspend fun getTopRatedMovies() = remoteDataSource.getTopRatedMovies()
}