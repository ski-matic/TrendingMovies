package com.learning.trendingmovies

import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.SearchResults
import com.learning.trendingmovies.data.TrendingResults
import io.reactivex.Single

//class MovieListRepository(application: Application) {
class MovieListRepository() {

    private val movieDatabaseAPI: MovieDatabaseAPI

    init {
        movieDatabaseAPI = MovieDatabaseAPI.create()
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

    fun getAllMovies(): Single<TrendingResults> {
        return movieDatabaseAPI.getMovies(
            MediaType.MOVIE.mediaTypeName,
            TimeWindow.DAY.timeWindowName
        )
    }

    fun getConfiguration(): Single<Configuration> {
        return movieDatabaseAPI.getConfiguration()
    }

    fun getSearchResults(queryString: String): Single<SearchResults> {
        // TODO: URL encode the string
        return movieDatabaseAPI.getSearchResults(queryString)
    }

}