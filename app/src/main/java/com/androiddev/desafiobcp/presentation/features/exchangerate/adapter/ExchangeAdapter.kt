package com.androiddev.desafiobcp.presentation.features.exchangerate.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev.desafiobcp.databinding.ItemExchangeBinding
import com.androiddev.desafiobcp.domain.models.ExchangeRate
import com.androiddev.desafiobcp.presentation.extensions.roundTo
import com.androiddev.desafiobcp.presentation.features.exchangerate.model.ExchangeRateModel

/**
 * @author Freddy Lazo.
 */
class ExchangeAdapter(val callback: (ExchangeRateModel) -> (Unit)) : RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>()  {

    private val list = mutableListOf<ExchangeRateModel>()

    fun addAll(lData: List<ExchangeRateModel>) {
        list.clear()
        list.addAll(lData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder = ExchangeViewHolder(parent)

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ExchangeViewHolder(private val parent: ViewGroup, private val binding: ItemExchangeBinding = ItemExchangeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(model: ExchangeRateModel) {
            with(binding) {
                flagImageView.setImageResource(model.image)
                nameCountryTextView.text = model.countryName
                exchangeValueTextView.text = "1 EUR = ${(1/model.exchangeValue).roundTo(2)} ${model.currencyAbbr}"
                itemView.setOnClickListener { callback.invoke(model) }
            }
        }

    }
}