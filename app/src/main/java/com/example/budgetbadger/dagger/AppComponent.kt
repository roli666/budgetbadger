package com.example.budgetbadger.dagger

import com.example.budgetbadger.application.BudgetBadgerApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: BudgetBadgerApplication)
}