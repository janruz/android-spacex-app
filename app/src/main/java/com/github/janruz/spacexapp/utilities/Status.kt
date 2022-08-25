package com.github.janruz.spacexapp.utilities

/**
 * Describes all the various status values a certain task can have
 */
enum class Status {
    /**
     * The task has been performed and it has succeeded.
     */
    SUCCESS,

    /**
     * The task has been performed and it has failed.
     */
    FAILURE,

    /**
     * The task has started, but there is no result yet.
     */
    LOADING,

    /**
     * The task has not even been started yet.
     */
    NONE
}