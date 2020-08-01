package com.example.budgetbadger.dagger

import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.adapters.DateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class APIModule {

    private fun moshi(): Moshi = Moshi.Builder()
        .add(DateAdapter())
        .build()

    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .client(okHttpClient)
            .build()
    }
}