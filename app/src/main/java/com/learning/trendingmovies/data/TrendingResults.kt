package com.learning.trendingmovies.data

data class TrendingResults(
    var page: Int,
    var results: List<Movie>,
    var total_pages: Int,
    var total_results: Int
) {
    override fun toString(): String {
        return "TrendingResults(page=$page, results=${results.count()}, total_pages=$total_pages, total_results=$total_results)"
    }
}
