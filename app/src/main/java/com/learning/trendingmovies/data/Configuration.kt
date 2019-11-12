package com.learning.trendingmovies.data

data class Configuration(
    val images: Images
) {
    override fun toString(): String {
        return "Configuration(images=${images.base_url})"
    }
}

data class Images(
    var base_url: String,
    var secure_base_url: String,
    var backdrop_sizes: List<String>,
    var logo_sizes: List<String>,
    var poster_sizes: List<String>,
    var profile_sizes: List<String>,
    var still_sizes: List<String>
)
