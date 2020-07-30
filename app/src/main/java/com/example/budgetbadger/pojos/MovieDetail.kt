package com.example.budgetbadger.pojos

import android.graphics.Bitmap
import com.example.budgetbadger.entities.Movie
import java.util.*

data class MovieDetail(
    val id: Int,
    val budget: Int,
    val title: String,
    val overview: String,
    val vote_average: Float,
    val adult: Boolean,
    val poster_path: String,
    val release_date: Date
) {
    fun toMovie(image: Bitmap?): Movie {
        return Movie(id, title, overview, image, vote_average, budget)
    }
}