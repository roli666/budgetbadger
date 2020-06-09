package com.example.budgetbadger.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.budgetbadger.Entities.Movie
import com.example.budgetbadger.Interfaces.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val webService: WebService) {
    fun getMovies(queryString: String): LiveData<List<Movie>> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<List<Movie>>()
        webService.getMovies(queryString).enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                data.value = response.body()
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                TODO()
            }
        })
        return data
    }
}