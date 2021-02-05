package com.androiddev.desafiobcp.presentation.core

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androiddev.desafiobcp.presentation.di.activity.ActivityComponent
import com.androiddev.desafiobcp.presentation.di.activity.HasComponent
import javax.inject.Inject

/**
 * @author Freddy Lazo.
 */
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Suppress("UNCHECKED_CAST")
    fun getComponent(): ActivityComponent = (activity as HasComponent<ActivityComponent>).getComponent()

    protected fun showToast(
        contentText: Any,
        duration: Int = Toast.LENGTH_LONG
    ) {
        val text = when(contentText){
            is String -> contentText
            is Int -> getString(contentText)
            else -> ""
        }
        Toast.makeText(requireContext(), text, duration).show()
    }
}