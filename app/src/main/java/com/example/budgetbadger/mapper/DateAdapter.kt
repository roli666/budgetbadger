package com.example.budgetbadger.mapper

import android.annotation.SuppressLint
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateAdapter {

    @ToJson
    fun toJson(value: Date): String = FORMATTER.format(value)

    @FromJson
    fun fromJson(value: String): Date = FORMATTER.parse(value)

    companion object {
        @SuppressLint("ConstantLocale")
        private val FORMATTER =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }
}