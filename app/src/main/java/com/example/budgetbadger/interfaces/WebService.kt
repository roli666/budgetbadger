package com.example.budgetbadger.interfaces

import com.example.budgetbadger.model.MovieDetail
import com.example.budgetbadger.model.SearchResultBase
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("search/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") queryString: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean
    ): SearchResultBase

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetail
}
