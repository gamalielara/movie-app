package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.helper.Resource
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel (private val repository: MovieRepository): ViewModel() {

    fun getNowPlayingMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        try{
            emit(Resource.success(data = repository.getNowPlayingMovies()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, exception.message ?: "Error encountered."))
        }
    }

    fun getPopularMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        try{
            emit(Resource.success(data = repository.getPopularMovies()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, exception.message ?: "Error encountered."))
        }
    }
    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        try{
            emit(Resource.success(data = repository.getTopRatedMovies()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, exception.message ?: "Error encountered."))
        }
    }
}