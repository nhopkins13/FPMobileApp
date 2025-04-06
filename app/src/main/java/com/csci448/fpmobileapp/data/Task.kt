package com.csci448.fpmobileapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

data class Task (
    val title: String,
    val coins: Int = 5,
    val timeDue: LocalDate,
    val completed: MutableState<Boolean> = mutableStateOf(false)
)
