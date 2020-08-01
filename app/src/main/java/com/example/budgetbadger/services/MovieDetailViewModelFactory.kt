package com.example.budgetbadger.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetbadger.repositories.MovieRepository
import com.example.budgetbadger.viewmodels.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailViewModelFactory @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailViewModel(movieRepository, movieId) as T
}