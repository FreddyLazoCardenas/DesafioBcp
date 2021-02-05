package com.androiddev.desafiobcp.domain.core.functional

import com.androiddev.desafiobcp.domain.core.exception.Failure

/**
 * @author Freddy Lazo.
 */
sealed class Resource<out T : Any> {
    class Success<T : Any>(val data: T) : Resource<T>()
    class Error(val failure: Failure) : Resource<Nothing>()

    override fun toString(): String = when (this) {
        is Success<*> -> "Success [data=$data]"
        is Error -> "Error [failure=$failure]"
    }
}