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

    //TODO: return data only when available
    override fun getMovies(queryString: String, page: Int, adult: Boolean): List<Movie> {
        var data = listOf<Movie>()
        apiService.getMovies(BuildConfig.API_KEY, queryString, page, adult)
            .enqueue(object : Callback<SearchResultBase> {

                override fun onResponse(
                    call: Call<SearchResultBase>,
                    response: Response<SearchResultBase>
                ) {
                    if (response.isSuccessful) {
                        data = response.body()!!.results.map {
                            Movie(it.id, it.title, it.overview, null, it.vote_average, 0)
                        }
                    }
                }

                override fun onFailure(call: Call<SearchResultBase>, t: Throwable) {
                    TODO()
                }
            })
        return data
    }

    override fun getMovie(movieId: Int, apiKey: String): Movie? {
        lateinit var returnMovie: Movie
        apiService.getMovieDetail(movieId, BuildConfig.API_KEY)
            .enqueue(object : Callback<MovieDetail> {
                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    if (response.isSuccessful) {
                        val movieDetail = response.body()!!
                        returnMovie = Movie(
                            movieDetail.id,
                            movieDetail.title,
                            movieDetail.overview,
                            null,
                            movieDetail.vote_average,
                            movieDetail.budget
                        )
                    }
                }
            })
        return returnMovie
    }
}