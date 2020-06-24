package com.example.budgetbadger.helpers

import android.graphics.Bitmap

object BitmapHelper {
    fun makeEmptyColoredBitmap(width: Int, height: Int, color: Long): Bitmap {
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        image.eraseColor(color)
        return image
    }
}