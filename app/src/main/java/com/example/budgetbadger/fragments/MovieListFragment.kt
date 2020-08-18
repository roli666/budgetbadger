package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbadger.MainActivity
import com.example.budgetbadger.R
import com.example.budgetbadger.adapters.MovieItemAdapter
import com.example.budgetbadger.databinding.FragmentListBinding
import com.example.budgetbadger.model.Movie
import com.example.budgetbadger.viewmodels.MovieListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by activityViewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var callback: OnMovieTapListener
    private lateinit var movieAdapter: MovieItemAdapter

    interface OnMovieTapListener {
        fun onMovieSelected(movie: Movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        callback = activity as MainActivity

        setMovieAdapter(
            viewModel.movieList.value!!, getString(R.string.movie_list_text_on_empty)
        )

        viewModel.movieList.observe(viewLifecycleOwner, { movies ->
            if (movies.isEmpty()) {
                if (!binding.searchBar.query.isNullOrEmpty()) {
                    movieAdapter.textWhenEmpty =
                        getString(R.string.movie_list_no_result_text) + binding.searchBar.query
                }
            } else {
                movieAdapter.movies = movies
                movieAdapter.notifyDataSetChanged()
            }
        })

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }

        binding.movieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                with(binding.movieList.layoutManager as LinearLayoutManager) {
                    if (this.findLastCompletelyVisibleItemPosition() == viewModel.movieList.value?.size?.minus(
                            1
                        )
                    ) {
                        viewModel.loadMore()
                    }
                }
            }
        })

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            private val debouncePeriod: Long = 500
            private var searchJob: Job? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.run {
                    if (!query.isNullOrEmpty()) {
                        fetchMovies(query, 1)
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
                                fetchMovies(newText, 1)
                            }
                        }
                        return true
                    } else {
                        searchJob?.cancel()
                        movieAdapter.textWhenEmpty = getString(R.string.movie_list_text_on_empty)
                    }
                }
                return false
            }
        })

        return binding.root
    }

    private fun setMovieAdapter(movies: MutableList<Movie>, textOnEmptyList: String) {
        movieAdapter = MovieItemAdapter(movies, textOnEmptyList)
        movieAdapter.onItemClick = { movie ->
            viewModel.select(movie)
            callback.onMovieSelected(movie)
        }
    }
}
