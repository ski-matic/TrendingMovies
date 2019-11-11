package com.learning.trendingmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class MovieListViewModel constructor(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "MovieListViewModel"
    }

    //    private var repository: MovieListRepository = MovieListRepository(application)
    private var repository: MovieListRepository = MovieListRepository()
    private var disposables: CompositeDisposable = CompositeDisposable()

    private val configuration: MutableLiveData<Configuration> by lazy {
        MutableLiveData<Configuration>().also {
            fetchConfiguration()
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

    // Replace this with Rx zip
    fun fetchConfigurationAndMovies() {
        if (configuration.value == null) {
            disposables +=
                repository.getConfiguration()
                    .subscribe({
                        configuration.postValue(it)
                        fetchMovies()
                    }, {
                        Log.d(TAG, "fetchConfigurationAndMovies: error: " + it.message)
                        // Do something here such as posting a wrapped error to the livedata
                    })
        } else {
            fetchMovies()
        }
    }

    private fun fetchMovies() {
        disposables +=
            repository.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ trendingResults ->
                    // We should be gauranteed to have a configuration at this point because
                    // "fetchConfigurationAndMovies()" makes fetch movies only after the configuration
                    // is retrieved.  This should be replace with a Rx "zip" call though
                    val config = configuration.value!!
                    Movie.setPosterBaseURL(
                        config.images.secure_base_url,
                        config.images.poster_sizes
                    )
                    movies.postValue(trendingResults.results)
                }, {
                    Log.d(TAG, "$TAG: fetchMovies: error: " + it.message)
                    // Do something here such as posting a wrapped error to the livedata
                })
    }

    fun getConfiguration(): LiveData<Configuration> {
        return configuration
    }

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