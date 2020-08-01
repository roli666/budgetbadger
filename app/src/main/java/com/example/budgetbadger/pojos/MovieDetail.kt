package com.example.budgetbadger.pojos

import com.example.budgetbadger.entities.Movie
import java.util.Date

data class MovieDetail(
    val id: Int,
    val budget: Int,
    val title: String,
    val overview: String,
    val vote_average: Float,
    val adult: Boolean,
    val poster_path: String?,
    val release_date: Date
) {
    fun toMovie(): Movie {
        return Movie(id, title, overview, poster_path, vote_average, budget)
    }
}