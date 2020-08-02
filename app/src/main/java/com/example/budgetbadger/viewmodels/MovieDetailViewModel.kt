package com.example.budgetbadger.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.budgetbadger.interfaces.MovieRepository

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    var movieRepository: MovieRepository,
    var movieId: Int
) : ViewModel() {

    var movie = liveData(Dispatchers.IO) {
        emit(movieRepository.getMovie(movieId))
    }
}