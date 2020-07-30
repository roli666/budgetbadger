package com.example.budgetbadger.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.repositories.MovieRepository
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