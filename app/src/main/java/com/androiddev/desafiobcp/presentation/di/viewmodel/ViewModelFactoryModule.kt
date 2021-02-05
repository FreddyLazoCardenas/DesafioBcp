package com.androiddev.desafiobcp.presentation.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * @author Freddy Lazo.
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    @Suppress("UNUSED")
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}