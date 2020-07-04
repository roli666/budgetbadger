package com.example.budgetbadger.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.entities.SearchResult
import com.example.budgetbadger.interfaces.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val webService: WebService) {

    fun getMovies(queryString: String): LiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()
        webService.getMovies("", queryString, 1, false)
            .enqueue(object : Callback<List<SearchResult>> {

                override fun onResponse(
                    call: Call<List<SearchResult>>,
                    response: Response<List<SearchResult>>
                ) {
                    TODO(response.body().toString())
                }

                override fun onFailure(call: Call<List<SearchResult>>, t: Throwable) {
                    TODO()
                }
            })
        return data
    }
}