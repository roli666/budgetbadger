package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetbadger.adapters.MovieItemAdapter
import com.example.budgetbadger.databinding.ListFragmentBinding
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.viewmodels.MovieListSharedViewModel
import com.example.budgetbadger.helpers.BitmapHelper

class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListSharedViewModel
    private lateinit var binding: ListFragmentBinding

    lateinit var callback: OnMovieTapListener

    fun setOnMovieSelectedListener(callback: OnMovieTapListener) {
        this.callback = callback
    }

    interface OnMovieTapListener {
        fun onMovieSelected(movie: Movie)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(layoutInflater)

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = createMovieAdapter()
        }
        binding.movieList.adapter
        viewModel = activity?.run {
            ViewModelProvider(this).get(MovieListSharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        return binding.root
    }

    private fun createMovieAdapter(): MovieItemAdapter {
        var movieAdapter = MovieItemAdapter(
            listOf(
                Movie(
                    "Batman",
                    "Batman and the very long description",
                    BitmapHelper.makeEmptyColoredBitmap(300, 200, 0xffff0000),
                    6.9f,
                    100000
                )
            )
        )
        movieAdapter.onItemClick = { movie ->
            viewModel.select(movie)
            callback.onMovieSelected(movie)
        }
        return movieAdapter;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}