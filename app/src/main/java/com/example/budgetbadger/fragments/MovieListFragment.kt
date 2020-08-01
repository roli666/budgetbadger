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
import com.example.budgetbadger.R
import com.example.budgetbadger.adapters.MovieItemAdapter
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent
import com.example.budgetbadger.databinding.ListFragmentBinding
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.viewmodels.MovieListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

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
            binding.movieList.adapter = createMovieAdapter(
                movies,
                getString(R.string.movie_list_no_result_text) + binding.searchBar.query
            )
        })

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = createMovieAdapter(listOf(), getString(R.string.movie_list_text_on_empty))
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            private var debouncePeriod: Long = 500
            private var searchJob: Job? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.run {
                    if (!query.isNullOrEmpty()) {
                        fetchMovies(query)
                        return true
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.run {
                    if (!newText.isNullOrEmpty()) {
                        searchJob?.cancel()
                        searchJob = CoroutineScope(Dispatchers.Main).launch {
                            newText.let {
                                delay(debouncePeriod)
                                fetchMovies(newText)
                            }
                        }
                        return true
                    } else {
                        binding.movieList.apply {
                            adapter = createMovieAdapter(
                                listOf(),
                                getString(R.string.movie_list_text_on_empty)
                            )
                        }
                    }
                }
                return false
            }
        })

        return binding.root
    }

    private fun createMovieAdapter(movies: List<Movie>, textOnEmptyList: String): MovieItemAdapter {
        var movieAdapter = MovieItemAdapter(movies, textOnEmptyList)
        movieAdapter.onItemClick = { movie ->
            viewModel.select(movie)
            callback.onMovieSelected(movie)
        }
        return movieAdapter
    }
}