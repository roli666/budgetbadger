package com.example.budgetbadger.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.repositories.MovieRepository
import javax.inject.Inject

class MovieListViewModel : ViewModel() {

    private val selected = MutableLiveData<Movie>()
    val movieList = MutableLiveData<List<Movie>>()
    private val applicationGraph: AppComponent = DaggerAppComponent.create()

    @Inject
    lateinit var movieRepo: MovieRepository

    init {
        applicationGraph.inject(this)
    }


    fun select(item: Movie) {
        selected.value = item
    }

    fun fetchMovies(query: String) {
        movieList.value = movieRepo.getMovies(query)
    }
}