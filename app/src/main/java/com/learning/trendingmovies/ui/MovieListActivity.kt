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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.learning.trendingmovies.MovieListViewModel
import com.learning.trendingmovies.R
import com.learning.trendingmovies.data.Movie
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.no_connection.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [MovieDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
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

    private var twoPane: Boolean = false
    private lateinit var viewModel: MovieListViewModel
    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        disposables = CompositeDisposable()
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)

        if (!networkAvailable(this)) {
            Log.d(TAG, "$TAG: onCreate: network not available")
            no_connection.visibility = View.VISIBLE
            item_list.visibility = View.INVISIBLE
            return
        }

        fab.hide()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            viewModel.fetchConfigurationAndMovies()

        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        viewModel.getMovies().observe(this, Observer<List<Movie>> {
            Log.d("Richard-debug", "$TAG: movies: " + it.size)
            val adapter = item_list.adapter as MovieListRecyclerViewAdapter
            adapter.values = it
            adapter.notifyDataSetChanged()
        })

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = MovieListRecyclerViewAdapter(this, twoPane)
        recyclerView.layoutManager = GridLayoutManager(this, NUM_GRID_COLUMNS)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("Richard-debug", "$TAG: onQueryTextChange: " + newText)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("Richard-debug", "$TAG: onQueryTextSubmit: " + query)
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        Log.d("Richard-debug", "$TAG: onMenuItemActionCollapse: " + p0)
        return true
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        Log.d("Richard-debug", "$TAG: onMenuItemActionExpand: " + p0)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.apply {
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

    fun networkAvailable(context: Context): Boolean {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = conMgr.activeNetworkInfo ?: return false
        return if (!activeNetwork.isConnected) false else activeNetwork.isAvailable
    }
}
