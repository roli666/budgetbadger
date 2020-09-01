package com.example.budgetbadger.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetbadger.interfaces.MovieRepository
import com.example.budgetbadger.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel @ViewModelInject constructor(
    private var movieRepo: MovieRepository,
) : ViewModel() {

    private val selected = MutableLiveData<Movie>()
    private var page: Int = 0
    private var lastQuery: String = ""

    val resultText = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()

    init {
        movieList.postValue(listOf())
    }

    fun fetchMovies(query: String, page: Int) {
        this.page = page
        lastQuery = query
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepo.getMovies(query, page)
            movieList.postValue(movies)
        }
    }

    fun loadMore() {
        page++
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepo.getMovies(lastQuery, page)
            val oldMovies = movieList.value ?: listOf()
            movieList.postValue(oldMovies.plus(movies))
        }
    }

    fun select(item: Movie) {
        selected.value = item
    }
}
