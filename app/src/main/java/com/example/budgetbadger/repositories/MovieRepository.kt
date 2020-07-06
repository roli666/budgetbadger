package com.example.budgetbadger.repositories

import com.example.budgetbadger.dagger.Configuration
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.pojos.MovieDetail
import com.example.budgetbadger.pojos.SearchResultBase
import com.example.budgetbadger.helpers.BitmapHelper
import com.example.budgetbadger.helpers.toMovie
import com.example.budgetbadger.interfaces.WebService
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
        apiService.getMovies(Configuration.apiKey, queryString, page, adult)
            .enqueue(object : Callback<SearchResultBase> {

                override fun onResponse(
                    call: Call<SearchResultBase>,
                    response: Response<SearchResultBase>
                ) {
                    if (response.isSuccessful) {
                        for (i in response.body()?.results!!) {
                            data.add(
                                i.toMovie(
                                    MovieDetail(),
                                    BitmapHelper.makeEmptyColoredBitmap(300, 300, 0x0000ff00)
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<SearchResultBase>, t: Throwable) {
                    TODO()
                }
            })
        return data
    }
}