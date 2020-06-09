package com.example.budgetbadger.Interfaces

import com.example.budgetbadger.Entities.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("/search/movie")
    fun getMovies(@Query("queryString") queryString: String): Call<List<Movie>>
}