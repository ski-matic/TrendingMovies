package com.learning.trendingmovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.learning.trendingmovies.data.TrendingResults
import io.reactivex.Single

class MovieListViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var repository: MovieListRepository = MovieListRepository(application)

    fun fetchMovies(): Single<TrendingResults> {
        return repository.getAllMovies()
    }

}