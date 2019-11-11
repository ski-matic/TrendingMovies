package com.learning.trendingmovies.data

data class SearchResults(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
)
