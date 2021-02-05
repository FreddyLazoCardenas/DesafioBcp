package com.androiddev.desafiobcp.presentation.di.activity

import com.androiddev.desafiobcp.domain.features.GetExchangeRatesUseCase
import com.androiddev.desafiobcp.domain.repositories.AppRepository
import dagger.Module
import dagger.Provides

/**
 * @author Freddy Lazo.
 */
@Module
class UseCaseModule {

    //region exchange
    @PerActivity
    @Provides
    fun providesListEventsUseCase(
        appRepository: AppRepository
    ): GetExchangeRatesUseCase = GetExchangeRatesUseCase(appRepository)
    //endregion

}