package com.androiddev.desafiobcp.presentation.core

import android.app.Application
import com.androiddev.desafiobcp.presentation.di.application.AppComponent
import com.androiddev.desafiobcp.presentation.di.application.AppModule
import com.androiddev.desafiobcp.presentation.di.application.DaggerAppComponent

/**
 * @author Freddy Lazo.
 */
class BcpApp  : Application(){

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getComponent(): AppComponent = appComponent
}