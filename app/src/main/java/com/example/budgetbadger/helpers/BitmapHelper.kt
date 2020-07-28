package com.example.budgetbadger.helpers

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi

object BitmapHelper {
    @RequiresApi(Build.VERSION_CODES.Q)
    fun makeEmptyColoredBitmap(width: Int, height: Int, color: Long): Bitmap {
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        image.eraseColor(color)
        return image
    }
}