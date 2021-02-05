package com.androiddev.desafiobcp.presentation.di.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Freddy Lazo.
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context = application


}