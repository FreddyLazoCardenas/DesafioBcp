package com.androiddev.desafiobcp.presentation.features.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.androiddev.desafiobcp.R
import com.androiddev.desafiobcp.presentation.features.exchangerate.view.ExchangeRateActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToExchangeView()
    }

    private fun goToExchangeView() {
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(baseContext, ExchangeRateActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}