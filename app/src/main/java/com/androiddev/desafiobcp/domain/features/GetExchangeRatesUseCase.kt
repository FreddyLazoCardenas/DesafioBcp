package com.androiddev.desafiobcp.domain.features

import com.androiddev.desafiobcp.domain.core.functional.None
import com.androiddev.desafiobcp.domain.core.functional.Resource
import com.androiddev.desafiobcp.domain.core.interactor.UseCase
import com.androiddev.desafiobcp.domain.models.ExchangeRate
import com.androiddev.desafiobcp.domain.repositories.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Freddy Lazo.
 */
class GetExchangeRatesUseCase(private val appRepository: AppRepository) :
    UseCase<List<ExchangeRate>, None>()  {

    override suspend fun run(params: None): Flow<Resource<List<ExchangeRate>>> = flow {
        emit(appRepository.getExchangeCountry())
    }
}