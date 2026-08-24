package com.example.mytaskspro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mytaskspro_database.db"
                )
                .addCallback(DatabaseCallback())
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateInitialTasks(database.taskDao())
                    }
                }
            }

            private suspend fun populateInitialTasks(taskDao: TaskDao) {
                val now = System.currentTimeMillis()
                val todayEvening = now + 4 * 3600 * 1000L
                val tomorrow = now + 24 * 3600 * 1000L

                val seedTasks = listOf(
                    Task(
                        title = "🚀 Welcome to My Tasks Pro!",
                        description = "Explore Kanban board, Calendar grid, Pomodoro focus timer, and Analytics dashboard.",
                        category = TaskCategory.WORK.label,
                        priority = TaskPriority.HIGH.name,
                        dueDate = todayEvening,
                        dueTime = "06:00 PM",
                        kanbanStatus = KanbanStatus.IN_PROGRESS.name,
                        subtasksJson = """[{"id":"1","title":"Check out Kanban view","isCompleted":true},{"id":"2","title":"Try the Pomodoro timer","isCompleted":false},{"id":"3","title":"Customize themes in Settings","isCompleted":false}]""",
                        reminderEnabled = true
                    ),
                    Task(
                        title = "💻 Finalize Project Documentation",
                        description = "Draft system architecture details and export backup file.",
                        category = TaskCategory.WORK.label,
                        priority = TaskPriority.URGENT.name,
                        dueDate = tomorrow,
                        dueTime = "02:30 PM",
                        kanbanStatus = KanbanStatus.TODO.name
                    ),
                    Task(
                        title = "🏃‍♂️ 30 Min Daily Cardio Workout",
                        description = "Stay healthy and energized for peak performance.",
                        category = TaskCategory.FITNESS.label,
                        priority = TaskPriority.MEDIUM.name,
                        dueDate = now,
                        dueTime = "07:00 AM",
                        isCompleted = true,
                        completedAt = now - 3600 * 1000L,
                        kanbanStatus = KanbanStatus.DONE.name
                    ),
                    Task(
                        title = "🛒 Grocery Shopping",
                        description = "Buy fresh vegetables, fruits, almond milk, and protein bars.",
                        category = TaskCategory.SHOPPING.label,
                        priority = TaskPriority.LOW.name,
                        dueDate = tomorrow + 4 * 3600 * 1000L,
                        dueTime = "05:00 PM",
                        kanbanStatus = KanbanStatus.TODO.name,
                        subtasksJson = """[{"id":"g1","title":"Almond Milk","isCompleted":false},{"id":"g2","title":"Fresh Spinach","isCompleted":true}]"""
                    )
                )

                seedTasks.forEach { taskDao.insertTask(it) }
            }
        }
    }
}
