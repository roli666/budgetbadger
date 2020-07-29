package com.example.budgetbadger.interfaces

import com.example.budgetbadger.entities.Movie

interface IMovieRepository {
    fun getMovies(queryString: String, page: Int = 1, adult: Boolean = false): List<Movie>
    fun getMovie(movieId: Int, apiKey: String): Movie?
}
