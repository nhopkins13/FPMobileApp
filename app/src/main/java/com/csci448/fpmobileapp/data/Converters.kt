package com.csci448.fpmobileapp.data

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromEpochDay(epochDay: Long): LocalDate {
        return LocalDate.ofEpochDay(epochDay)
    }

    @TypeConverter
    fun localDateToEpochDay(date: LocalDate): Long {
        return date.toEpochDay()
    }
}
