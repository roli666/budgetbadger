package com.example.budgetbadger.dagger

import com.example.budgetbadger.application.BudgetBadgerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: BudgetBadgerApplication) {
    @Provides
    @Singleton
    fun provideApp() = app
}