package com.csci448.fpmobileapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csci448.fpmobileapp.data.daos.ItemsDao
import com.csci448.fpmobileapp.data.daos.TaskDao

@Database(
    entities = [
        Task::class,
        ShopItem::class
    ],
    version = 3
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun itemsDao(): ItemsDao
}

