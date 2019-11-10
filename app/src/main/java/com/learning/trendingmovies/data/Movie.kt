package com.learning.trendingmovies.data

data class Movie(
    var poster_path: String?,
    var adult: Boolean,
    var overview: String,
    var release_date: String,
    var genre_ids: List<Int>,
    var id: Int,
    var original_title: String,
    var original_language: String,
    var title: String,
    var backdrop_path: String?,
    var popularity: Float,
    var vote_count: Int,
    var video: Boolean,
    var vote_average: Float
) {
    companion object {
        private const val TAG = "Movie"
        private const val POSTER_SIZE_INDEX = 2
        private var posterBaseURL: String? = null

        fun setPosterBaseURL(baseUrl: String, posterSizes: List<String>) {
            val posterSize = posterSizes[POSTER_SIZE_INDEX]
            posterBaseURL = "$baseUrl$posterSize"
        }
    }

    fun getPosterUrl(): String {
        return "$posterBaseURL$poster_path"
    }

    override fun toString(): String {
        return "Movie(title='$title')"
    }

}

