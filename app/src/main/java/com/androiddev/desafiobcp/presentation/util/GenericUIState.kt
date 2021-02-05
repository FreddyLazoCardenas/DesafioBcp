package com.androiddev.desafiobcp.presentation.util

import com.androiddev.desafiobcp.domain.core.exception.Failure

/**
 * @author Freddy Lazo.
 */
abstract class GenericUIState<T>(
    val showProgress: Boolean,
    val success: Event<T>?,
    val failure: Event<Failure>?
)