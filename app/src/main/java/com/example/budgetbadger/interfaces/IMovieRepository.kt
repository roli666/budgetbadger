package com.example.budgetbadger.interfaces

import com.example.budgetbadger.entities.Movie

interface IMovieRepository {
    suspend fun getMovies(queryString: String, page: Int = 1, adult: Boolean = false): List<Movie>
    suspend fun getMovie(movieId: Int): Movie?
}
