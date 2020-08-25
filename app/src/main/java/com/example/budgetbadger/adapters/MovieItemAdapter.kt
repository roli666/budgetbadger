package com.example.budgetbadger.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.R
import com.example.budgetbadger.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieItemAdapter(
    var movies: List<Movie>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    fun notifyChanges(oldList: List<Movie>, newList: List<Movie>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        })
        diff.dispatchUpdatesTo(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as MovieItemViewHolder) {
            mTitle.text = movies[position].title
            mBudget.text = "${movies[position].budget} $"
            mRating.text = "${movies[position].rating}\\10"
            if (!movies[position].poster_path.isNullOrEmpty()) {
                Glide.with(holder.itemView)
                    .load(BuildConfig.IMAGE_BASE_URL + movies[position].poster_path)
                    .into(mPoster)
            }
            mDescription.text = movies[position].description
        }
    }

    override fun getItemCount(): Int = movies.size

    var onItemClick: ((Movie) -> Unit)? = null
}
