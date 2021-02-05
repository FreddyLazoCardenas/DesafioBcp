package com.androiddev.desafiobcp.presentation.di.activity

import com.androiddev.desafiobcp.data.repositories.AppRepositoryImpl
import com.androiddev.desafiobcp.domain.repositories.AppRepository
import dagger.Module
import dagger.Provides

/**
 * @author Freddy Lazo.
 */
@Module
class RepositoryModule {

    @PerActivity
    @Provides
    fun providesEventsRepository(
    ): AppRepository = AppRepositoryImpl()
}