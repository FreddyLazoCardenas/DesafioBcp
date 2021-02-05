package com.androiddev.desafiobcp.presentation.features.exchangerate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev.desafiobcp.R
import com.androiddev.desafiobcp.presentation.core.BaseActivity

/**
 * @author Freddy Lazo.
 */
class ExchangeRateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getComponent().inject(this@ExchangeRateActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rate)
    }
}