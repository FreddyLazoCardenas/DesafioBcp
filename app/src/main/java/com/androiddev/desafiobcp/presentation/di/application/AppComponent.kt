package com.androiddev.desafiobcp.presentation.di.application

import android.content.Context
import dagger.Component
import javax.inject.Singleton

/**
 * @author Freddy Lazo.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getContext(): Context
}