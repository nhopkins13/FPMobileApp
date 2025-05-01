package com.csci448.fpmobileapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val coins: Int = 5,
    val timeDue: LocalDate,
    val completed: Boolean = false,
    val archived: Boolean = false
)
