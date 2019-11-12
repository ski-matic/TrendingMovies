package com.learning.trendingmovies.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.learning.trendingmovies.MovieListViewModel
import com.learning.trendingmovies.R
import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.Movie
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.no_connection.*

class MovieListActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    MenuItem.OnActionExpandListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    companion object {
        private const val TAG = "MovieListActivity"
        private const val NUM_GRID_COLUMNS = 3
    }

    private lateinit var viewModel: MovieListViewModel
    private lateinit var disposables: CompositeDisposable

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        // Check if the network is available, if not, show an error
        if (!networkAvailable(this)) {
            Log.d(TAG, "$TAG: onCreate: network not available")
            no_connection.visibility = View.VISIBLE
            item_list.visibility = View.INVISIBLE
            return
        }

        disposables = CompositeDisposable()
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)

        // Observe the configuration so if one is received we can set the base URLs on the Movie object
        viewModel.getConfiguration().observe(this, Observer<Configuration> {
            Movie.setPosterBaseURL(
                it.images.secure_base_url,
                it.images.poster_sizes,
                it.images.backdrop_sizes
            )
        })

        // When a list of movies is set, update the recyclerView with the items
        viewModel.getMovies().observe(this, Observer<List<Movie>> {
            Log.d(TAG, "$TAG: movies: " + it.size)
            val adapter = item_list.adapter as MovieListRecyclerViewAdapter
            adapter.values = it
            adapter.notifyDataSetChanged()
        })

        item_list.adapter = MovieListRecyclerViewAdapter(this)

        // It would be nice to not have the number of columns hard coded, it doesn't
        // display well in portrait
        item_list.layoutManager = GridLayoutManager(this, NUM_GRID_COLUMNS)
    }

    /**
     * If the search text changes, don't do anything.
     * We could potentially search as they type
     */
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    /***
     * This is called to clear the adapter when a new list of movies is set
     */
    private fun clearAdapter() {
        val adapter = item_list.adapter as MovieListRecyclerViewAdapter
        adapter.values = emptyList()
        adapter.notifyDataSetChanged()
        item_list.scrollToPosition(0)
    }

    /***
     * When a search is submitted, fetch the results, clear the current list of movies
     * and clear the focus from the search bar (otherwise the keyboard will popup unexpectantly)
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.fetchSearchResults(query)
            clearAdapter()
            searchView?.clearFocus()
        }
        return false
    }

    /***
     * When the search is close, get the trending movies back again
     */
    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        viewModel.fetchConfigurationAndMovies()
        clearAdapter()
        return true
    }

    /***
     * Nothing specified is done when the search bar is opened
     */
    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        return true
    }

    /***
     * Ensure that the search icon is displayed
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as SearchView
        searchView?.apply {
            isSubmitButtonEnabled = true
            queryHint = getString(R.string.query_hint)
            setOnQueryTextListener(this@MovieListActivity)
        }
        menuItem.setOnActionExpandListener(this)
        return true
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    /***
     * Convenience funciton to check if the network is available
     */
    private fun networkAvailable(context: Context): Boolean {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = conMgr.activeNetworkInfo ?: return false
        return if (!activeNetwork.isConnected) false else activeNetwork.isAvailable
    }
}
