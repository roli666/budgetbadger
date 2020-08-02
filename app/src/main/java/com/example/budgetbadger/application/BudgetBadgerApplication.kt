package com.example.budgetbadger.application

import android.app.Application
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent

class BudgetBadgerApplication : Application() {

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}