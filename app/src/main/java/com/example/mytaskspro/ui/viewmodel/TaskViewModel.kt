package com.example.mytaskspro.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytaskspro.data.AppDatabase
import com.example.mytaskspro.data.KanbanStatus
import com.example.mytaskspro.data.Task
import com.example.mytaskspro.data.TaskCategory
import com.example.mytaskspro.data.TaskPriority
import com.example.mytaskspro.data.TaskRepository
import com.example.mytaskspro.notification.NotificationHelper
import com.example.mytaskspro.ui.theme.AppThemeMode
import com.example.mytaskspro.util.DataExportImport
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AnalyticsState(
    val totalTasks: Int = 0,
    val completedTasks: Int = 0,
    val pendingTasks: Int = 0,
    val overdueTasks: Int = 0,
    val completionRate: Float = 0f,
    val streakDays: Int = 1
)

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    private val notificationHelper: NotificationHelper

    val searchQuery = MutableStateFlow("")
    val selectedCategory = MutableStateFlow("ALL")
    val selectedPriority = MutableStateFlow("ALL")
    val currentTheme = MutableStateFlow(AppThemeMode.SYSTEM)

    // Pomodoro Timer State
    val pomodoroTimeLeft = MutableStateFlow(25 * 60) // 25 min in seconds
    val isPomodoroRunning = MutableStateFlow(false)
    val pomodoroMode = MutableStateFlow("WORK") // WORK, SHORT_BREAK, LONG_BREAK

    init {
        val database = AppDatabase.getDatabase(application)
        repository = TaskRepository(database.taskDao())
        notificationHelper = NotificationHelper(application)
    }

    val tasksStream: StateFlow<List<Task>> = combine(
        repository.allTasks,
        searchQuery,
        selectedCategory,
        selectedPriority
    ) { tasks, query, category, priority ->
        tasks.filter { task ->
            val matchesQuery = query.isBlank() ||
                    task.title.contains(query, ignoreCase = true) ||
                    task.description.contains(query, ignoreCase = true)

            val matchesCategory = category == "ALL" || task.category.equals(category, ignoreCase = true)
            val matchesPriority = priority == "ALL" || task.priority.equals(priority, ignoreCase = true)

            matchesQuery && matchesCategory && matchesPriority
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val analyticsState: StateFlow<AnalyticsState> = repository.allTasks.map { tasks ->
        val total = tasks.size
        val completed = tasks.count { it.isCompleted }
        val pending = tasks.count { !it.isCompleted }
        val overdue = tasks.count { it.isOverdue() }
        val rate = if (total > 0) (completed.toFloat() / total) * 100f else 0f

        AnalyticsState(
            totalTasks = total,
            completedTasks = completed,
            pendingTasks = pending,
            overdueTasks = overdue,
            completionRate = rate,
            streakDays = if (completed > 0) 3 else 1
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AnalyticsState())

    fun addTask(
        title: String,
        description: String,
        category: String,
        priority: TaskPriority,
        dueDate: Long?,
        dueTime: String?,
        subtasksJson: String,
        reminderEnabled: Boolean
    ) {
        if (title.isBlank()) return
        viewModelScope.launch {
            val task = Task(
                title = title.trim(),
                description = description.trim(),
                category = category,
                priority = priority.name,
                dueDate = dueDate,
                dueTime = dueTime,
                subtasksJson = subtasksJson,
                reminderEnabled = reminderEnabled
            )
            val id = repository.insertTask(task)
            if (reminderEnabled && dueDate != null) {
                notificationHelper.scheduleAlarm(task.copy(id = id))
            }
        }
    }

    fun quickAddTask(rawInput: String) {
        if (rawInput.isBlank()) return
        var title = rawInput
        var category = TaskCategory.PERSONAL.label
        var priority = TaskPriority.MEDIUM

        if (rawInput.contains("#Work", ignoreCase = true)) category = TaskCategory.WORK.label
        if (rawInput.contains("#Fitness", ignoreCase = true)) category = TaskCategory.FITNESS.label
        if (rawInput.contains("#Study", ignoreCase = true)) category = TaskCategory.STUDY.label
        if (rawInput.contains("#Shopping", ignoreCase = true)) category = TaskCategory.SHOPPING.label

        if (rawInput.contains("!High", ignoreCase = true)) priority = TaskPriority.HIGH
        if (rawInput.contains("!Urgent", ignoreCase = true)) priority = TaskPriority.URGENT
        if (rawInput.contains("!Low", ignoreCase = true)) priority = TaskPriority.LOW

        title = title.replace(Regex("#\\w+|!\\w+"), "").trim()

        addTask(
            title = if (title.isBlank()) rawInput else title,
            description = "Quick Added Task",
            category = category,
            priority = priority,
            dueDate = null,
            dueTime = null,
            subtasksJson = "[]",
            reminderEnabled = false
        )
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            repository.toggleTaskCompletion(task)
        }
    }

    fun updateKanbanStatus(task: Task, newStatus: String) {
        viewModelScope.launch {
            repository.updateKanbanStatus(task, newStatus)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            notificationHelper.cancelAlarm(task.id)
            repository.deleteTask(task)
        }
    }

    fun clearCompleted() {
        viewModelScope.launch {
            repository.clearCompleted()
        }
    }

    fun changeTheme(mode: AppThemeMode) {
        currentTheme.value = mode
    }

    fun exportBackupJson(): String {
        return DataExportImport.exportToJson(tasksStream.value)
    }

    fun importBackupJson(json: String) {
        val tasks = DataExportImport.importFromJson(json)
        viewModelScope.launch {
            tasks.forEach { repository.insertTask(it) }
        }
    }

    // Pomodoro Controls
    fun startPomodoro() {
        isPomodoroRunning.value = true
    }

    fun pausePomodoro() {
        isPomodoroRunning.value = false
    }

    fun resetPomodoro(mode: String = "WORK") {
        isPomodoroRunning.value = false
        pomodoroMode.value = mode
        pomodoroTimeLeft.value = when (mode) {
            "SHORT_BREAK" -> 5 * 60
            "LONG_BREAK" -> 15 * 60
            else -> 25 * 60
        }
    }
}
