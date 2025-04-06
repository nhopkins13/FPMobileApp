package com.csci448.fpmobileapp.data

import androidx.compose.runtime.MutableState
import java.time.LocalDateTime

data class Task (
    val title: String,
    val coins: Int,
    val timeDue: LocalDateTime,
    val completed: MutableState<Boolean>
)
