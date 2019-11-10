package com.learning.trendingmovies.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learning.trendingmovies.R
import com.learning.trendingmovies.data.Movie
import kotlinx.android.synthetic.main.item_list_content.view.*

class MovieListRecyclerViewAdapter(
    private val parentActivity: MovieListActivity,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder>() {

    var values: List<Movie> = emptyList()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            Log.d("Richard-debug", "clicked on: " + item)
            if (twoPane) {
                val fragment = MoveDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(MoveDetailFragment.ARG_ITEM_ID, item.id.toString())
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, MovieDetailActivity::class.java).apply {
                    putExtra(MoveDetailFragment.ARG_ITEM_ID, item.id)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.release_date

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}