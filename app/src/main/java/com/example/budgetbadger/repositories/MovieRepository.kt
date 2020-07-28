package com.example.budgetbadger.repositories

import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.interfaces.WebService
import com.example.budgetbadger.pojos.SearchResult
import com.example.budgetbadger.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private var retrofit: Retrofit
) {
    private val apiService: WebService = retrofit.create(WebService::class.java)

    fun getMovies(queryString: String, page: Int = 1, adult: Boolean = false): List<Movie> {
        val data = mutableListOf<Movie>()
        apiService.getMovies(BuildConfig.API_KEY, queryString, page, adult)
            .enqueue(object : Callback<SearchResult> {

                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    if (response.isSuccessful) {
                        TODO()
                    }
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    TODO()
                }
            })
        return data
    }
}