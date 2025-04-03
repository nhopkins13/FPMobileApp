package com.csci448.fpmobileapp.data

import android.util.MutableBoolean
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

data class Task (
    val title: String,
    val coins: Int,
    val timeDue: String,
    val completed: MutableState<Boolean>
)
