package com.androiddev.desafiobcp.domain.repositories

import com.androiddev.desafiobcp.domain.core.functional.Resource
import com.androiddev.desafiobcp.domain.models.ExchangeRate

/**
 * @author Freddy Lazo.
 */
interface AppRepository {

    fun getExchangeCountry() : Resource<List<ExchangeRate>>
}