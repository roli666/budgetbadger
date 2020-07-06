package com.example.budgetbadger.dagger

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@GlideModule
class GlideModule : AppGlideModule() {

    @Provides
    @Singleton
    fun provideGlide() = this
}