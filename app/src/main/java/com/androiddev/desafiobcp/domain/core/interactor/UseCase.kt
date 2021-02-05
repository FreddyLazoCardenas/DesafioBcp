package com.androiddev.desafiobcp.domain.core.interactor

import com.androiddev.desafiobcp.domain.core.functional.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Freddy Lazo.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    /**
     * Method to be executed in another scope
     */
    abstract suspend fun run(params: Params): Flow<Resource<Type>>

    /**
     *  Only method to be executed in the use case.
     */
    suspend operator fun invoke(params: Params): Flow<Resource<Type>> = run(params)
}