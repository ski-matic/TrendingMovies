package com.learning.trendingmovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.learning.trendingmovies.R
import com.learning.trendingmovies.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*

class MovieListRecyclerViewAdapter(
    private val parentActivity: MovieListActivity
) : RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "MovieListRecyclerViewAdapter"
    }

    var values: List<Movie> = emptyList()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            MovieDetailsPopupWindow.show(parentActivity.frameLayout, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        Picasso.with(holder.itemView.context)
            .load(item.getPosterFullUrl())
            .placeholder(R.drawable.poster_loading)
            .into(holder.posterImage)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val posterImage: ImageView = view.poster
    }
}