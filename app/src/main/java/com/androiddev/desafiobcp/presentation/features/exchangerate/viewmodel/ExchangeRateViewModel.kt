package com.androiddev.desafiobcp.presentation.features.exchangerate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddev.desafiobcp.R
import com.androiddev.desafiobcp.domain.core.exception.Failure
import com.androiddev.desafiobcp.domain.core.exception.MoneyReceivedFoundFailure
import com.androiddev.desafiobcp.domain.core.functional.None
import com.androiddev.desafiobcp.domain.core.functional.Resource
import com.androiddev.desafiobcp.domain.features.GetExchangeRatesUseCase
import com.androiddev.desafiobcp.domain.models.ExchangeRate
import com.androiddev.desafiobcp.presentation.di.activity.PerActivity
import com.androiddev.desafiobcp.presentation.features.exchangerate.model.ExchangeRateModel
import com.androiddev.desafiobcp.presentation.util.Event
import com.androiddev.desafiobcp.presentation.util.GenericUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Freddy Lazo.
 */
@PerActivity
class ExchangeRateViewModel @Inject constructor(private val getExchangeRatesUseCase: GetExchangeRatesUseCase) :
    ViewModel() {

    private val _listExchangeUIState = MutableLiveData<ListExchangeUIState>()
    val listExchangeUIState: LiveData<ListExchangeUIState>
        get() = _listExchangeUIState

    private val _moneyReceived = MutableLiveData<MoneyReceivedUIState>()
    val moneyReceived: LiveData<MoneyReceivedUIState>
        get() = _moneyReceived

    var topValue = MutableLiveData<ExchangeRateModel?>()
    var bottomValue = MutableLiveData<ExchangeRateModel?>()
    var shouldConsumeData = MutableLiveData(false)

    fun listExchangeData() {
        viewModelScope.launch {
            val flow = getExchangeRatesUseCase(None())
            emitListExchangeUIState(showProgress = true)
            delay(1000)
            flow.collect { resource ->
                when (resource) {
                    is Resource.Success -> emitListExchangeUIState(
                        showProgress = false,
                        success = Event(handleListExchangeSuccess(resource.data))
                    )
                    is Resource.Error -> emitListExchangeUIState(
                        showProgress = false,
                        failure = Event(resource.failure)
                    )
                }
            }
        }
    }

    private fun handleListExchangeSuccess(list: List<ExchangeRate>): List<ExchangeRateModel> {
        return list.map {
            ExchangeRateModel(
                it.countryName,
                it.image,
                it.currencyName,
                it.exchangeValue,
                it.currencyAbbr
            )
        }
    }

    fun requestExchange(sentValue: String) {
        if (validateFields(sentValue)) performOperation(sentValue)
    }

    private fun performOperation(sentValue: String) {
        emitMoneyReceivedUIState(
            showProgress = false,
            success = Event(
                sentValue.toFloat() * ((topValue.value?.exchangeValue
                    ?: 1f) / (bottomValue.value?.exchangeValue ?: 1f))
            )
        )
    }

    private fun validateFields(sentValue: String): Boolean {
        return when {
            topValue.value == null -> {
                emitMoneyReceivedUIState(
                    showProgress = false,
                    failure = Event(MoneyReceivedFoundFailure.apply { error = R.string.top_missing })
                )
                false
            }
            bottomValue.value == null -> {
                emitMoneyReceivedUIState(
                    showProgress = false,
                    failure = Event(MoneyReceivedFoundFailure.apply { error = R.string.bottom_missing })
                )
                false
            }
            sentValue.isEmpty() -> {
                emitMoneyReceivedUIState(
                    showProgress = false,
                    failure = Event(MoneyReceivedFoundFailure.apply { error = R.string.input_value_missing })
                )
                false
            }
            else -> true
        }
    }

    private fun emitListExchangeUIState(
        showProgress: Boolean = false,
        success: Event<List<ExchangeRateModel>>? = null,
        failure: Event<Failure>? = null
    ) {
        val uistate = ListExchangeUIState(showProgress, success, failure)
        _listExchangeUIState.value = uistate
    }

    private fun emitMoneyReceivedUIState(
        showProgress: Boolean = false,
        success: Event<Float>? = null,
        failure: Event<Failure>? = null
    ) {
        val uistate = MoneyReceivedUIState(showProgress, success, failure)
        _moneyReceived.value = uistate
    }

    inner class ListExchangeUIState(
        showProgress: Boolean,
        success: Event<List<ExchangeRateModel>>?,
        failure: Event<Failure>?
    ) :
        GenericUIState<List<ExchangeRateModel>>(showProgress, success, failure)

    inner class MoneyReceivedUIState(
        showProgress: Boolean,
        success: Event<Float>?,
        failure: Event<Failure>?
    ) :
        GenericUIState<Float>(showProgress, success, failure)

}