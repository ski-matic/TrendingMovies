package com.learning.trendingmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class MovieListViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val TAG = "MovieListViewModel"
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
            fetchMovies()
        }
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    fun fetchMovies() {
        disposables +=
            repository.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Richard-debug", "$TAG: fetchMovies: " + it)
                    movies.postValue(it.results)
                }, {
                    Log.d("Richard-debug", "$TAG: fetchMovies: error: " + it.message)
                })
    }

    fun getConfiguration(): LiveData<Configuration> {
        return configuration
    }

    fun fetchConfiguration() {
        disposables +=
            repository.getConfiguration()
                .subscribe({
                    configuration.postValue(it)
                }, {
                    Log.d(TAG, "fetchConfiguration: error: " + it.message)
                })
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}