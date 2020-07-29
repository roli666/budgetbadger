package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetbadger.adapters.MovieItemAdapter
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent
import com.example.budgetbadger.databinding.ListFragmentBinding
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.repositories.MovieRepository
import com.example.budgetbadger.viewmodels.MovieListViewModel
import javax.inject.Inject

class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private val applicationGraph: AppComponent = DaggerAppComponent.create()
    private lateinit var viewModel: MovieListViewModel
    private lateinit var binding: ListFragmentBinding

    private lateinit var callback: OnMovieTapListener

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
        applicationGraph.inject(this)
        binding = ListFragmentBinding.inflate(layoutInflater)
        viewModel = requireActivity().run {
            ViewModelProvider(this).get(MovieListViewModel::class.java)
        }

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movies ->
            binding.movieList.adapter = createMovieAdapter(movies)
        })

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(activity)
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.run {
                    if (query != null) {
                        fetchMovies(query)
                        return true
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.run {
                    if (newText != null) {
                        fetchMovies(newText)
                        return true
                    }
                }
                return false
            }

        })

        return binding.root
    }

    private fun createMovieAdapter(movies: List<Movie>): MovieItemAdapter {
        var movieAdapter = MovieItemAdapter(movies, "empty list")
        movieAdapter.onItemClick = { movie ->
            viewModel.select(movie)
            callback.onMovieSelected(movie)
        }
        return movieAdapter;
    }
}