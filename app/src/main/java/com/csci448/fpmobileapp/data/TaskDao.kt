package com.csci448.fpmobileapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    // only show tasks where archived = 0
    @Query("""
    SELECT *
      FROM tasks
     WHERE archived = 0
  ORDER BY timeDue ASC
  """)
    fun getAllTasks(): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Query("UPDATE tasks SET archived = 1 WHERE completed = 1 AND archived = 0")
    suspend fun archiveCompletedTasks()

    @Query("SELECT * FROM tasks WHERE archived = 1 ORDER BY timeDue DESC")
    fun getArchivedTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): Task?
}
