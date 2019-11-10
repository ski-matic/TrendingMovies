package com.learning.trendingmovies

import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.TrendingResults
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDatabaseAPI {

    companion object {
        private const val API_KEY = "d0bfa2d663af7a94e515085e33ab9615"

        fun create(): MovieDatabaseAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

            return retrofit.create(MovieDatabaseAPI::class.java)
        }
    }

    @GET("trending/{mediaType}/{timeWindow}?api_key=$API_KEY")
    fun getMovies(
        @Path("mediaType") mediaType: String,
        @Path("timeWindow") timeWindow: String
    ): Single<TrendingResults>

    @GET("configuration?api_key=$API_KEY")
    fun getConfiguration(): Single<Configuration>

}