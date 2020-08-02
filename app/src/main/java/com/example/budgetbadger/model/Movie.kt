package com.example.budgetbadger.model

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val poster_path: String?,
    val rating: Float,
    val budget: Int
)