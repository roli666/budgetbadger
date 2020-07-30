package com.example.budgetbadger.viewmodels

import androidx.lifecycle.*
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel : ViewModel() {

    private val selected = MutableLiveData<Movie>()
    private val applicationGraph: AppComponent = DaggerAppComponent.create()

    val movieList = MutableLiveData<List<Movie>>()

    fun fetchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepo.getMovies(query)
            movieList.postValue(movies)
        }
    }

    @Inject
    lateinit var movieRepo: MovieRepository

    init {
        applicationGraph.inject(this)
    }


    fun select(item: Movie) {
        selected.value = item
    }
}