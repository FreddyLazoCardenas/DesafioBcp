package com.androiddev.desafiobcp.presentation.core

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androiddev.desafiobcp.presentation.di.activity.ActivityComponent
import com.androiddev.desafiobcp.presentation.di.activity.DaggerActivityComponent
import com.androiddev.desafiobcp.presentation.di.activity.HasComponent
import javax.inject.Inject

/**
 * @author Freddy Lazo.
 */
abstract class BaseActivity : AppCompatActivity(), HasComponent<ActivityComponent> {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val activityComponent by lazy {
        DaggerActivityComponent
                .builder()
                .appComponent((application as BcpApp).getComponent())
                .build()
    }

    override fun getComponent(): ActivityComponent = activityComponent
}