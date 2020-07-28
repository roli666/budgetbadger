package com.example.budgetbadger.interfaces

import com.example.budgetbadger.pojos.MovieDetail
import com.example.budgetbadger.pojos.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("search/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") queryString: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean
    ): Call<SearchResult>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetail>

}