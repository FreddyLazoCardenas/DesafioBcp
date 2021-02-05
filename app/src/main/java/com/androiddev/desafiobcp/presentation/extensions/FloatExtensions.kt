package com.androiddev.desafiobcp.presentation.extensions

/**
 * @author Freddy Lazo.
 */
fun Float.roundTo(n : Int) : Float {
    return "%.${n}f".format(this).toFloat()
}