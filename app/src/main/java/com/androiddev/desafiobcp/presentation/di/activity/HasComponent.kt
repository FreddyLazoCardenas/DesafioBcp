package com.androiddev.desafiobcp.presentation.di.activity

/**
 * @author Freddy Lazo.
 */
interface HasComponent<C> {
    fun getComponent(): C
}