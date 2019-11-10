package com.learning.trendingmovies

import android.app.Application
import io.reactivex.Single

class MovieListRepository(application: Application) {

    private val movieDatabaseAPI: MovieDatabaseAPI

    init {
        movieDatabaseAPI = MovieDatabaseAPI.create()
    }

    fun getAllMovies(): Single<TrendingResults> {
        // mediaType:  "all", "movie", "tv", "person"
        // timewindow: "day", "week"
        return movieDatabaseAPI.getMovies("movie", "day")
    }
}