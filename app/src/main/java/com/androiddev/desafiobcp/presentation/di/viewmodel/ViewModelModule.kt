package com.androiddev.desafiobcp.presentation.di.viewmodel

import androidx.lifecycle.ViewModel
import com.androiddev.desafiobcp.presentation.features.exchangerate.viewmodel.ExchangeRateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Freddy Lazo.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExchangeRateViewModel::class)
    @Suppress("UNUSED")
    abstract fun bindsWelcomeViewModel(exchangeRateViewModel: ExchangeRateViewModel): ViewModel

}