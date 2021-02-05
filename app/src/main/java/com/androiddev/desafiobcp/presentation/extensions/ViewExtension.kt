package com.androiddev.desafiobcp.presentation.extensions

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Group

/**
 * @author Freddy Lazo.
 */
fun View.setLogicVisibility(bool: Boolean) {
    this.visibility = if (bool) View.VISIBLE else View.GONE
}
