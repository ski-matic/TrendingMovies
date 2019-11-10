package com.learning.trendingmovies.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.learning.trendingmovies.MovieListViewModel
import com.learning.trendingmovies.R
import com.learning.trendingmovies.data.Configuration
import com.learning.trendingmovies.data.Movie
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [MovieDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MovieListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private val TAG = "MovieListActivity"
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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

//            fetchConfiguration()
            viewModel.fetchConfiguration()

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

        viewModel.getConfiguration().observe(this, Observer<Configuration> {
            Log.d("Richard-debug", "$TAG: configuration: " + it)
        })

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter =
            MovieListRecyclerViewAdapter(this, twoPane)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

}
