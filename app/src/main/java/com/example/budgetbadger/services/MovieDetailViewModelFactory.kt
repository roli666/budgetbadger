package com.example.budgetbadger.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent
import com.example.budgetbadger.interfaces.MovieRepository
import com.example.budgetbadger.viewmodels.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailViewModelFactory constructor(
    private val movieId: Int
) : ViewModelProvider.Factory {

    @Inject
    lateinit var movieRepository: MovieRepository
    private val applicationGraph: AppComponent = DaggerAppComponent.create()

    init {
        applicationGraph.inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailViewModel(movieRepository, movieId) as T
}