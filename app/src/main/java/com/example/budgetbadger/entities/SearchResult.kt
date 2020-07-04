package com.example.budgetbadger.entities

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.util.*

data class SearchResult(
    @SerializedName("poster_path") val PosterPath: String,
    @SerializedName("id") val Id: String,
    @SerializedName("overview") val Description: String,
    @SerializedName("release_date") val ReleaseDate: Date,
    @SerializedName("adult") val Adult: Boolean,
    @SerializedName("vote_average") val rating: Float
)