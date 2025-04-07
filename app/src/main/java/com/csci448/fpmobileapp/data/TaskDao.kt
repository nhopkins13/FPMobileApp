package com.csci448.fpmobileapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY timeDue ASC")
    fun getAllTasks(): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM tasks WHERE completed = 1")
    suspend fun deleteAllCompleted()
}
