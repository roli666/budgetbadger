package com.example.budgetbadger.services

import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.interfaces.MovieRepository
import com.example.budgetbadger.interfaces.WebService
import com.example.budgetbadger.model.Movie
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    retrofit: Retrofit
) : MovieRepository {
    private val apiService: WebService = retrofit.create(WebService::class.java)

    override suspend fun getMovies(queryString: String, page: Int, adult: Boolean): List<Movie> =
        apiService.getMovies(BuildConfig.API_KEY, queryString, page, adult).results.map {
            Movie(it.id, it.title, it.overview, it.poster_path, it.vote_average, 0)
        }

    override suspend fun getMovie(movieId: Int): Movie =
        apiService.getMovieDetail(movieId, BuildConfig.API_KEY).toMovie()
}
