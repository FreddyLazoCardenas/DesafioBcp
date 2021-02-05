package com.androiddev.desafiobcp.presentation.di.activity

import com.androiddev.desafiobcp.presentation.di.application.AppComponent
import com.androiddev.desafiobcp.presentation.di.viewmodel.ViewModelFactoryModule
import com.androiddev.desafiobcp.presentation.di.viewmodel.ViewModelModule
import com.androiddev.desafiobcp.presentation.features.exchangerate.view.ExchangeRateActivity
import com.androiddev.desafiobcp.presentation.features.exchangerate.view.ExchangeRateFragment
import com.androiddev.desafiobcp.presentation.features.exchangerate.view.ExchangeRateListFragment
import dagger.Component

/**
 * @author Freddy Lazo.
 */
@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [RepositoryModule::class, UseCaseModule::class, ViewModelFactoryModule::class, ViewModelModule::class]
)
interface ActivityComponent {

    fun inject(activity: ExchangeRateActivity)

    fun inject(fragment: ExchangeRateFragment)
    fun inject(fragment: ExchangeRateListFragment)
}