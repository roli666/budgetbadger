package com.example.budgetbadger.adapters

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbadger.R
import com.example.budgetbadger.entities.Movie
import kotlinx.android.synthetic.main.list_row.view.*

class MovieItemAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {

    inner class MovieItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mTitle: TextView = view.movieTitle
        val mPoster: ImageView = view.moviePoster
        val mDescription: TextView = view.movieDescription
        val mRating: TextView = view.movieRating
        val mBudget: TextView = view.movieBudget

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_row, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val currentItem = movies[position]
        with(holder)
        {
            mTitle.text = currentItem.title
            mBudget.text = currentItem.budget.toString() + " $"
            mRating.text = currentItem.rating.toString() + "\\10"
            mPoster.setImageBitmap(currentItem.poster)
            mDescription.text = currentItem.description
        }
    }

    override fun getItemCount(): Int = movies.size

    var onItemClick: ((Movie) -> Unit)? = null

}