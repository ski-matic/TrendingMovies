package com.learning.trendingmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.TrendingResults
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

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

    fun fetchMovies(): Single<TrendingResults> {
        return repository.getAllMovies()
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