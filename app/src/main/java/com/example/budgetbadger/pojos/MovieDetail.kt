package com.example.budgetbadger.pojos

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
)