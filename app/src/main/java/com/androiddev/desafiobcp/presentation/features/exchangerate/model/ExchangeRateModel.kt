package com.androiddev.desafiobcp.presentation.features.exchangerate.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

/**
 * @author Freddy Lazo.
 */
@Parcelize
data class ExchangeRateModel(
    val countryName: String,
    @DrawableRes val image: Int,
    val currencyName : String,
    val exchangeValue: Float,
    val currencyAbbr : String,
    var fromTop: Boolean = false
) : Parcelable