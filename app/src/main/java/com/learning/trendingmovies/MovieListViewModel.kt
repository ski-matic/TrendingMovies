package com.learning.trendingmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.Movie
import com.learning.trendingmovies.data.TrendingResults
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class MovieListViewModel constructor(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "MovieListViewModel"
    }

    private var repository: MovieListRepository = MovieListRepository()
    private var disposables: CompositeDisposable = CompositeDisposable()

    private val configuration: MutableLiveData<Configuration> by lazy {
        MutableLiveData<Configuration>().also {
            fetchConfigurationAndMovies()
        }
    }

    private val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            fetchConfigurationAndMovies()
        }
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    /***
     * This checks if the configuration exists yet or not, if not it fetches it followed by
     * fetching the list of movies.
     *
     * This should be replaced with something like Rx zip
     */
    fun fetchConfigurationAndMovies() {

        if (disposables.size() == 0) {
            disposables += Single.zip(
                repository.getConfiguration(),
                repository.getAllMovies(),
                BiFunction<Configuration, TrendingResults, Pair<Configuration, TrendingResults>> { configuration, trendingResult ->
                    Pair(configuration, trendingResult)
                }
            )
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    // It's important to post the configuration before the movies, since the movies use the configuration
                    configuration.postValue(it.first)

                    Thread.sleep(2000)
                    movies.postValue(it.second.results)
                }, {
                    Log.d(TAG, "fetchConfigurationAndMovies: error: " + it.message)
                    // Do something here such as posting a wrapped error to the livedata
                })
        }
    }

    /***
     * Fetches the list of movies and posts the results to the LiveData
     */
    private fun fetchMovies() {
        disposables +=
            repository.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ trendingResults ->
                    movies.postValue(trendingResults.results)
                }, {
                    Log.d(TAG, "$TAG: fetchMovies: error: " + it.message)
                    // Do something here such as posting a wrapped error to the livedata
                })
    }

    fun getConfiguration(): LiveData<Configuration> {
        return configuration
    }

    /***
     * Fetches the configuration and posts the result to the LiveData
     */
    private fun fetchConfiguration() {
        disposables +=
            repository.getConfiguration()
                .subscribe({
                    configuration.postValue(it)
                }, {
                    Log.d(TAG, "fetchConfiguration: error: " + it.message)
                    // Do something here such as posting a wrapped error to the livedata
                })
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    /***
     * Fetches the search results and posts them to the LiveData
     */
    fun fetchSearchResults(queryString: String) {
        disposables +=
            repository.getSearchResults(queryString)
                .subscribe({
                    movies.postValue(it.results)
                }, {
                    Log.d(TAG, "$TAG: fetchSearchResults: error: " + it.message)
                    // Do something here such as posting a wrapped error to the livedata
                })
    }
}