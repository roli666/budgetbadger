package com.example.budgetbadger.entities

import android.graphics.Bitmap

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val poster: Bitmap?,
    val rating: Float,
    val budget: Int
)