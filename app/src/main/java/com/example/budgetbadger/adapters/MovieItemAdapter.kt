package com.example.budgetbadger.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbadger.R
import com.example.budgetbadger.databinding.ActivityMainBinding
import com.example.budgetbadger.databinding.ListRowBinding
import com.example.budgetbadger.entities.Movie

class MovieItemAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {

    class MovieItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_row, parent, false)) {
        private var binding: ListRowBinding = ListRowBinding.inflate(inflater)
        private var mTitle: TextView
        private var mPoster: ImageView
        private var mDescription: TextView
        private var mRating: TextView
        private var mBudget: TextView

        init {
            mTitle = binding.movieTitle
            mPoster = binding.moviePoster
            mDescription = binding.movieDescription
            mRating = binding.movieRating
            mBudget = binding.movieBudget
        }

        fun bind(movie: Movie) {
            binding.movieTitle.apply {
                text = movie.title
            }

            binding.moviePoster.setImageBitmap(movie.poster)
            binding.movieDescription.text = movie.description
            binding.movieRating.text = movie.rating.toString() + "/10"
            binding.movieBudget.text = movie.budget.toString() + " $"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieItemViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie: Movie = movies[position]
        holder.bind(movie)
    }

}