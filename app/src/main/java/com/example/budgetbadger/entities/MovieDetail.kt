package com.example.budgetbadger.entities

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("budget") val budget: Int
)