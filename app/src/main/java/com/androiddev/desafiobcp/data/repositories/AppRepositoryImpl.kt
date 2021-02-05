package com.androiddev.desafiobcp.data.repositories

import com.androiddev.desafiobcp.R
import com.androiddev.desafiobcp.domain.core.functional.Resource
import com.androiddev.desafiobcp.domain.models.ExchangeRate
import com.androiddev.desafiobcp.domain.repositories.AppRepository

/**
 * @author Freddy Lazo.
 */
class AppRepositoryImpl : AppRepository {

    override fun getExchangeCountry(): Resource<List<ExchangeRate>> {
        return Resource.Success(
            listOf(
                ExchangeRate("European Union", R.drawable.european_union, "Euros", 1.0f , "EUR"),
                ExchangeRate("United States", R.drawable.united_states, "Dolares", 0.833f, "USD"),
                ExchangeRate("Japan", R.drawable.japan, "Yenes", 0.0086f,"JPY"),
                ExchangeRate("United Kingdom", R.drawable.united_kingdom, "Dolares", 0.833f,"GBP"),
                ExchangeRate("Switzerland", R.drawable.switzerland, "Franco Suizo", 1.0f,"CHF"),
                ExchangeRate("Canada", R.drawable.canada, "Dolares", 0.833f,"CAD"),
                ExchangeRate("Peru", R.drawable.ic_peru, "Soles", 0.25f,"PEN")
            )
        )
    }
}