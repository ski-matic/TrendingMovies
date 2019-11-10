package com.learning.trendingmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Single

class MovieListViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var repository: MovieListRepository = MovieListRepository(application)

    fun fetchMovies(): Single<TrendingResults> {
        Log.d("Richard-debug", "fetchMovies: Thread: " + Thread.currentThread().name)
        return repository.getAllMovies()
    }

}