package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbadger.MainActivity
import com.example.budgetbadger.R
import com.example.budgetbadger.adapters.MovieItemAdapter
import com.example.budgetbadger.databinding.FragmentListBinding
import com.example.budgetbadger.model.Movie
import com.example.budgetbadger.viewmodels.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMovieAdapter(listOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        callback = activity as MainActivity

        return binding.root
    }

    private fun setMovieAdapter(movies: List<Movie>) {
        movieAdapter = MovieItemAdapter(movies)
        movieAdapter.onItemClick = { movie ->
            viewModel.select(movie)
            callback.onMovieSelected(movie)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.resultText.postValue(getString(R.string.movie_list_text_on_empty))

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movies ->
            if (movies.isEmpty()) {
                if (!search_bar.query.isNullOrEmpty()) {
                    viewModel.resultText.postValue(getString(R.string.movie_list_no_result_text) + search_bar.query)
                }
            } else {
                viewModel.resultText.postValue("")
            }
            movieAdapter.notifyChanges(movieAdapter.movies, movies)
        })

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        binding.movieList.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
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

        binding.searchBar.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener,
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
                            viewModel.resultText.postValue(getString(R.string.movie_list_text_on_empty))
                        }
                    }
                    return false
                }
            })

        viewModel.resultText.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.textToDisplay.text = it
                binding.textToDisplay.visibility = View.GONE
            } else {
                binding.textToDisplay.text = it
                binding.textToDisplay.visibility = View.VISIBLE
            }
        })
    }
}
