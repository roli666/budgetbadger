package com.example.budgetbadger.dagger

import com.example.budgetbadger.application.BudgetBadgerApplication
import com.example.budgetbadger.fragments.MovieDetailFragment
import com.example.budgetbadger.fragments.MovieListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        GlideModule::class,
        APIModule::class
    ]
)
interface AppComponent {
    fun inject(app: BudgetBadgerApplication)
    fun inject(movieList: MovieListFragment)
}