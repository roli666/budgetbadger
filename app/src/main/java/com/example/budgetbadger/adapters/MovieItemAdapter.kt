package com.example.budgetbadger.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbadger.R
import com.example.budgetbadger.entities.Movie
import kotlinx.android.synthetic.main.empty_view_holder.view.*
import kotlinx.android.synthetic.main.list_row.view.*

class MovieItemAdapter(
    private val movies: List<Movie>,
    private val textWhenEmpty: String
) :
    RecyclerView.Adapter<MovieItemAdapter.BaseViewHolder>() {

    inner class MovieItemViewHolder(view: View) :
        BaseViewHolder(view) {
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

    inner class EmptyViewHolder(view: View) : BaseViewHolder(view) {
        val textToDisplay: TextView = view.textToDisplay
    }

    abstract inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (movies.isEmpty()) {
            return EmptyViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.empty_view_holder, parent, false)
            )
        }
        return MovieItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_row, parent, false)
        )
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = movies[position]
        when (holder) {
            is EmptyViewHolder -> {
                with(holder)
                {
                    textToDisplay.text = textWhenEmpty
                }
            }
            is MovieItemViewHolder -> {
                with(holder)
                {
                    mTitle.text = currentItem.title
                    mBudget.text = "${currentItem.budget} $"
                    mRating.text = "${currentItem.rating}\\10"
                    mPoster.setImageBitmap(currentItem.poster)
                    mDescription.text = currentItem.description
                }
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    var onItemClick: ((Movie) -> Unit)? = null

}