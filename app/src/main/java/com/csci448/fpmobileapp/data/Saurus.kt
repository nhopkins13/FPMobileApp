package com.csci448.fpmobileapp.data

/**
 * Holds information about the user's Saurus
 *
 * may need to add other data classes for these attributes
 *
 * TODO:
 *  redo it better, current is placeholder
 */
data class Saurus(
    val name: String,
    val type: String = "T-Rex",
    val size: Int = 1,
    val hat: Int = 0,
    val color: Int = 0,
)
