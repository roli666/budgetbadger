package com.example.budgetbadger.application

import android.app.Application
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.AppModule
import com.example.budgetbadger.dagger.DaggerAppComponent

class BudgetBadgerApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}