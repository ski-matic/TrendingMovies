package com.learning.trendingmovies

import android.app.Application
import com.learning.trendingmovies.data.TrendingResults
import io.reactivex.Single

class MovieListRepository(application: Application) {

    private val movieDatabaseAPI: MovieDatabaseAPI

    init {
        movieDatabaseAPI = MovieDatabaseAPI.create()
    }

    fun getAllMovies(): Single<TrendingResults> {
        return movieDatabaseAPI.getMovies(
            MediaType.MOVIE.mediaTypeName,
            TimeWindow.DAY.timeWindowName
        )
    }

    enum class MediaType(val mediaTypeName: String) {
        ALL("all"),
        MOVIE("movie"),
        TV("tv"),
        PERSON("person");
    }

    enum class TimeWindow(val timeWindowName: String) {
        DAY("day"),
        WEEK("week");
    }
}