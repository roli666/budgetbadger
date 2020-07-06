package com.example.budgetbadger.helpers

import android.graphics.Bitmap
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.pojos.MovieDetail
import com.example.budgetbadger.pojos.SearchResult

fun SearchResult.toMovie(detail: MovieDetail, image: Bitmap) = Movie(
    this.title,
    this.overview,
    image,
    this.vote_average.toFloat(),
    detail.budget
)