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
    private var movieRepo: MovieRepository
) : ViewModel() {

    private val selected = MutableLiveData<Movie>()

    val movieList = MutableLiveData<List<Movie>>()

    fun fetchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepo.getMovies(query)
            movieList.postValue(movies)
        }
    }

    fun select(item: Movie) {
        selected.value = item
    }
}