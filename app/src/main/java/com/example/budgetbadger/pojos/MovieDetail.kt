package com.example.budgetbadger.pojos

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val backdrop_path: String = "",
    @SerializedName("belongs_to_collection") val belongs_to_collection: BelongsToCollection = BelongsToCollection(
        0,
        "",
        "",
        ""
    ),
    @SerializedName("budget") val budget: Int = 0,
    @SerializedName("genres") val genres: List<Genres> = listOf(),
    @SerializedName("homepage") val homepage: String = "",
    @SerializedName("id") val id: Int = 0,
    @SerializedName("imdb_id") val imdb_id: String = "",
    @SerializedName("original_language") val original_language: String = "",
    @SerializedName("original_title") val original_title: String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("popularity") val popularity: Double = 0.0,
    @SerializedName("poster_path") val poster_path: String = "",
    @SerializedName("production_companies") val production_companies: List<ProductionCompanies> = listOf(),
    @SerializedName("production_countries") val production_countries: List<ProductionCountries> = listOf(),
    @SerializedName("release_date") val release_date: String = "",
    @SerializedName("revenue") val revenue: Int = 0,
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("spoken_languages") val spoken_languages: List<SpokenLanguages> = listOf(),
    @SerializedName("status") val status: String = "",
    @SerializedName("tagline") val tagline: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("vote_average") val vote_average: Int = 0,
    @SerializedName("vote_count") val vote_count: Int = 0
)