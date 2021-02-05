package com.androiddev.desafiobcp.domain.core.exception

import androidx.annotation.StringRes

/**
 * @author Freddy Lazo.
 * Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure(@StringRes var error: Int = 0) {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}