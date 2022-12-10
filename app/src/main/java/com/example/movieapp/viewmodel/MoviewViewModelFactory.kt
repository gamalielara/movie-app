package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.movieapp.helper.RemoteDataSource
import com.example.movieapp.repository.MovieRepository

class MoviewViewModelFactory(val remoteDataSource: RemoteDataSource) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(MovieRepository(remoteDataSource)) as T
        }
        throw (IllegalArgumentException("Illegal class name!"))
    }

}