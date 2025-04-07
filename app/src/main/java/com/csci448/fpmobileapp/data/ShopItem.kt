package com.csci448.fpmobileapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "items")
data class ShopItem (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val imageId: Int,
    val coinCost: Int = 5,
    val owned: Boolean = false
)
