package com.example.budgetbadger.dagger

import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.mapper.DateAdapter
import com.example.budgetbadger.interfaces.MovieRepository
import com.example.budgetbadger.repository.MovieRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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

    @Provides
    @Singleton
    fun provideMovieRepository(retrofit: Retrofit): MovieRepository {
        return MovieRepositoryImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .client(okHttpClient)
            .build()
    }
}