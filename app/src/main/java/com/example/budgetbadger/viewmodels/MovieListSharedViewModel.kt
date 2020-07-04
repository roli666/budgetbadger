package com.example.budgetbadger.viewmodels

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budgetbadger.entities.Movie

class MovieListSharedViewModel : ViewModel() {
    val selected = MutableLiveData<Movie>()

    fun select(item: Movie) {
        selected.value = item
    }
}