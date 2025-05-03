package com.csci448.fpmobileapp.data.daos

import androidx.room.*
import com.csci448.fpmobileapp.data.ShopItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<ShopItem>>

    @Query("SELECT * FROM items")
    suspend fun getAllItemsOnce(): List<ShopItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: ShopItem)

    @Update
    suspend fun updateItem(item: ShopItem)
}
