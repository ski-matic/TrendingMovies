package com.learning.trendingmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.trendingmovies.data.Movie
import com.learning.trendingmovies.R
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MoveDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {

                val id = it.getString(ARG_ITEM_ID)

                activity?.toolbar_layout?.title = item?.title
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.item_detail.text = it.release_date
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
