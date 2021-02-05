package com.androiddev.desafiobcp.domain.models

import androidx.annotation.DrawableRes

/**
 * @author Freddy Lazo.
 */
data class ExchangeRate(
    val countryName: String,
    @DrawableRes val image: Int,
    val currencyName : String,
    val exchangeValue: Float,
    val currencyAbbr : String
)
