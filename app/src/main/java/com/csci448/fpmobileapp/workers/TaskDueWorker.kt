package com.csci448.fpmobileapp.workers // Adjust package name as needed

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room // Import Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.csci448.fpmobileapp.MainActivity // Import your main activity
import com.csci448.fpmobileapp.MyApplication // Import Application class for channel ID
import com.csci448.fpmobileapp.R // Import R for resources
import com.csci448.fpmobileapp.data.AppDatabase // Import DB
import com.csci448.fpmobileapp.data.daos.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDueWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    private val TAG = "TaskDueWorker"

    companion object {
        const val KEY_TASK_ID = "task_id" // Key for input data
    }


    private val taskDao: TaskDao by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_app_db" // Use the SAME database name as in MainActivity
        ).build().taskDao()
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val taskId = inputData.getInt(KEY_TASK_ID, -1)
        Log.d(TAG, "Worker running for Task ID: $taskId")

        if (taskId == -1) {
            Log.e(TAG, "Invalid Task ID received.")
            return@withContext Result.failure()
        }

        try {
            val task = taskDao.getTaskById(taskId) // Use suspend function

            if (task == null) {
                Log.w(TAG, "Task with ID $taskId not found (might have been deleted).")
                return@withContext Result.success() // Task gone, work is successful
            }

            // Check if task is already completed or archived
            if (task.completed || task.archived) {
                Log.d(TAG, "Task $taskId (${task.title}) already completed/archived. No notification needed.")
                return@withContext Result.success()
            }

            Log.d(TAG, "Task $taskId (${task.title}) is due. Preparing notification.")
            // Task is valid and due, show notification
            showNotification(applicationContext, task.id, task.title)

            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "Error getting task or showing notification for ID $taskId", e)
            Result.failure() // Use failure for unexpected errors
        }
    }

    private fun showNotification(context: Context, taskId: Int, taskTitle: String) {
        // Create Intent to open MainActivity when notification is clicked
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            taskId, // Use taskId as request code for uniqueness
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, MyApplication.TASK_REMINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your notification icon
            .setContentTitle("Task Due: $taskTitle")
            .setContentText("Don't forget to complete your task!")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // High priority for reminders
            .setAutoCancel(true) // Dismiss notification when tapped
            .setContentIntent(pendingIntent) // Set the intent that fires when tapped

        // Check for permission before notifying (important for Android 13+)
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.w(TAG, "Cannot show notification - POST_NOTIFICATIONS permission not granted.")
            // Consider how to handle this - maybe log, maybe try requesting again (difficult from Worker)
            return
        }

        // Use a unique ID for each notification (e.g., the task ID)
        // so multiple notifications can appear if tasks are due close together
        NotificationManagerCompat.from(context).notify(taskId, builder.build())
        Log.i(TAG, "Notification shown for Task ID: $taskId")
    }
}