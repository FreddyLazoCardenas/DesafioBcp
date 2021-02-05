package com.androiddev.desafiobcp.presentation.features.exchangerate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.androiddev.desafiobcp.R
import com.androiddev.desafiobcp.databinding.FragmentExchangeListBinding
import com.androiddev.desafiobcp.presentation.core.BaseFragment
import com.androiddev.desafiobcp.presentation.core.KEY_DATA
import com.androiddev.desafiobcp.presentation.extensions.setLogicVisibility
import com.androiddev.desafiobcp.presentation.features.exchangerate.adapter.ExchangeAdapter
import com.androiddev.desafiobcp.presentation.features.exchangerate.model.ExchangeRateModel
import com.androiddev.desafiobcp.presentation.features.exchangerate.viewmodel.ExchangeRateViewModel

/**
 * @author Freddy Lazo.
 */
class ExchangeRateListFragment : BaseFragment() {

    private var _binding: FragmentExchangeListBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<ExchangeRateViewModel> { factory }
    private val args: ExchangeRateListFragmentArgs by navArgs()
    private val adapter by lazy {
        ExchangeAdapter {
            it.fromTop = args.isFromTop
            itemExchangeClicked(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        requestData()
    }

    private fun requestData() {
        viewmodel.listExchangeData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getComponent().inject(this@ExchangeRateListFragment)
        super.onCreate(savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewmodel.listExchangeUIState.observe(this, {
            binding.viewProgressBar.setLogicVisibility(it.showProgress)
            it.success?.peek()?.let { data ->
                listExchangeData(data)
            }
        })
    }

    private fun listExchangeData(it: List<ExchangeRateModel>) {
        adapter.addAll(it)
    }

    private fun itemExchangeClicked(model: ExchangeRateModel) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_DATA, model)
        requireActivity().onBackPressed()
    }

    private fun setUpView() {
        with(binding) {
            exchangeRecyclerView.adapter = adapter
            val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(requireContext(), R.drawable.item_divider_padding)?.let { itemDecoration.setDrawable(it) }
            exchangeRecyclerView.addItemDecoration(itemDecoration)
        }
    }

}