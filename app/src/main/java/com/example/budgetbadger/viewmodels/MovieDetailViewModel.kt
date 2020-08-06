package com.example.budgetbadger.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetbadger.interfaces.MovieRepository
import com.example.budgetbadger.model.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private var movieRepository: MovieRepository
) : ViewModel() {

    var movie: MutableLiveData<Movie> = MutableLiveData()

    fun fetchMovie(movieId: Int) {
        viewModelScope.launch {
            movie.postValue(movieRepository.getMovie(movieId))
        }
    }
}