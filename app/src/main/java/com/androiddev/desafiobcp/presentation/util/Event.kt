package com.androiddev.desafiobcp.presentation.util

/**
 * @author Freddy Lazo.
 */
open class Event<T>(private val content: T) {

    var consumed = false
        private set

    /**
     * Consumes the content if it's not been consumed yet.
     * @return The unconsumed content or 'null' if it was consumed already.
     */
    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }

    /**
     * @return The content whether it's been handled or not.
     */
    fun peek(): T = content

    override fun equals(other: Any?): Boolean {
        other as Event<*>
        return when {
            this === other -> true
            javaClass != other.javaClass -> false
            content != other.content -> false
            consumed != other.consumed -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + consumed.hashCode()
        return result
    }
}