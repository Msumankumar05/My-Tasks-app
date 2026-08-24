package com.example.mytaskspro.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.mytaskspro.data.AppDatabase
import com.example.mytaskspro.data.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        val taskId = intent.getLongExtra(NotificationHelper.EXTRA_TASK_ID, -1L)

        if (taskId == -1L) return

        val database = AppDatabase.getDatabase(context)
        val repository = TaskRepository(database.taskDao())
        val notificationHelper = NotificationHelper(context)

        when (action) {
            "com.example.mytaskspro.ACTION_TASK_REMINDER" -> {
                val title = intent.getStringExtra(NotificationHelper.EXTRA_TASK_TITLE) ?: "Task Reminder"
                CoroutineScope(Dispatchers.IO).launch {
                    val task = repository.getTaskById(taskId)
                    if (task != null && !task.isCompleted) {
                        notificationHelper.showTaskNotification(taskId, title, task.description)
                    }
                }
            }
            "com.example.mytaskspro.ACTION_COMPLETE_TASK" -> {
                CoroutineScope(Dispatchers.IO).launch {
                    val task = repository.getTaskById(taskId)
                    if (task != null) {
                        repository.toggleTaskCompletion(task)
                    }
                }
            }
        }
    }
}
