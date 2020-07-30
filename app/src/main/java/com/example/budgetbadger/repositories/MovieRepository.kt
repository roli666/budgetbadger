package com.example.budgetbadger.repositories

import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.interfaces.WebService
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.interfaces.IMovieRepository
import com.example.budgetbadger.pojos.MovieDetail
import com.example.budgetbadger.pojos.SearchResultBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    retrofit: Retrofit
) : IMovieRepository {
    private val apiService: WebService = retrofit.create(WebService::class.java)

    override suspend fun getMovies(queryString: String, page: Int, adult: Boolean): List<Movie> =
        apiService.getMovies(BuildConfig.API_KEY, queryString, page, adult).results.map {
            Movie(it.id, it.title, it.overview, null, it.vote_average, 0)
        }

    override suspend fun getMovie(movieId: Int): Movie =
        apiService.getMovieDetail(movieId, BuildConfig.API_KEY).toMovie(null)
}