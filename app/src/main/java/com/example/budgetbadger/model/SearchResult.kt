package com.example.budgetbadger.model

data class SearchResult(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Float,
    val adult: Boolean,
    val poster_path: String?
)