package com.androiddev.desafiobcp.presentation.features.exchangerate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.androiddev.desafiobcp.R
import com.androiddev.desafiobcp.databinding.FragmentExchangeRateBinding
import com.androiddev.desafiobcp.presentation.core.BaseFragment
import com.androiddev.desafiobcp.presentation.core.KEY_DATA
import com.androiddev.desafiobcp.presentation.extensions.roundTo
import com.androiddev.desafiobcp.presentation.features.exchangerate.model.ExchangeRateModel
import com.androiddev.desafiobcp.presentation.features.exchangerate.viewmodel.ExchangeRateViewModel

/**
 * @author Freddy Lazo.
 */
class ExchangeRateFragment : BaseFragment(), View.OnLongClickListener, View.OnClickListener {

    private var _binding: FragmentExchangeRateBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<ExchangeRateViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeRateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        fillCurrentValues()
    }

    private fun fillCurrentValues() {
        with(binding) {
            bottomCurrencyTextView.text = viewmodel.bottomValue.value?.currencyName
            topCurrencyTextView.text = viewmodel.topValue.value?.currencyName
            setExchangeRate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getComponent().inject(this@ExchangeRateFragment)
        super.onCreate(savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ExchangeRateModel>(
            KEY_DATA
        )?.observe(this, { itemSelected(it) })
        viewmodel.moneyReceived.observe(this, { setResultValue(it) })
    }

    private fun setResultValue(it: ExchangeRateViewModel.MoneyReceivedUIState) {
        it.success?.peek()?.let { data ->
            binding.youReceiveEditText.setText(data.roundTo(3).toString())
        }
        it.failure?.consume()?.let {
            showToast(it.error)
        }
    }

    private fun itemSelected(model: ExchangeRateModel) {
        if (viewmodel.shouldConsumeData.value == true) {
            viewmodel.shouldConsumeData.value = false
            with(binding) {
                if (model.fromTop) {
                    topCurrencyTextView.text = model.currencyName
                    viewmodel.topValue.value = model
                } else {
                    bottomCurrencyTextView.text = model.currencyName
                    viewmodel.bottomValue.value = model
                }
                setExchangeRate()
            }
        }
    }

    private fun setExchangeRate() {
        val value = ((viewmodel.topValue.value?.exchangeValue
            ?: 0f) / (viewmodel.bottomValue.value?.exchangeValue ?: 0f))
        if (!value.isNaN() && !value.isInfinite() && value != 0f) binding.exchangeRateTextView.text =
            getString(R.string.exchange_rate, value.roundTo(3).toString())
    }

    private fun setUpListeners() {
        with(binding) {
            topCurrencyTextView.setOnLongClickListener(this@ExchangeRateFragment)
            bottomCurrencyTextView.setOnLongClickListener(this@ExchangeRateFragment)
            exchangeCardView.setOnClickListener(this@ExchangeRateFragment)
            operationButton.setOnClickListener(this@ExchangeRateFragment)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            R.id.topCurrencyTextView -> {
                viewmodel.shouldConsumeData.value = true
                val action =
                    ExchangeRateFragmentDirections.actionExchangeRateFragmentToExchangeRateListFragment(
                        true
                    )
                findNavController().navigate(action)
            }
            R.id.bottomCurrencyTextView -> {
                viewmodel.shouldConsumeData.value = true
                val action =
                    ExchangeRateFragmentDirections.actionExchangeRateFragmentToExchangeRateListFragment(
                        false
                    )
                findNavController().navigate(action)
            }
        }
        return true
    }

    private fun exchangeValues() {
        exchangeViewModelValues()
        exchangeTextValues()
        setExchangeRate()
    }

    private fun exchangeViewModelValues() {
        val tmpBottom = viewmodel.bottomValue.value
        val tmpTop = viewmodel.topValue.value
        viewmodel.topValue.value = tmpBottom
        viewmodel.bottomValue.value = tmpTop
    }

    private fun exchangeTextValues() {
        with(binding) {
            topCurrencyTextView.text = viewmodel.topValue.value?.currencyName
            bottomCurrencyTextView.text = viewmodel.bottomValue.value?.currencyName
        }
    }

    private fun requestExchange() {
        viewmodel.requestExchange(binding.youSendEditText.text.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.exchangeCardView -> exchangeValues()
            R.id.operationButton -> requestExchange()
        }
    }
}