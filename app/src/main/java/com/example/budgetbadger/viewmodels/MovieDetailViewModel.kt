package com.example.budgetbadger.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.repositories.MovieRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    var movieRepository: MovieRepository,
    var movieId: Int
) : ViewModel() {

    lateinit var movie: MutableLiveData<Movie>

    init {
        movie.apply {
            value = movieRepository.getMovie(movieId, BuildConfig.API_KEY)
        }
    }
}