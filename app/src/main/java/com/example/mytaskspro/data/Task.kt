package com.example.mytaskspro.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

enum class TaskPriority(val label: String, val colorHex: String, val level: Int) {
    LOW("Low", "#4CAF50", 1),
    MEDIUM("Medium", "#2196F3", 2),
    HIGH("High", "#FF9800", 3),
    URGENT("Urgent", "#F44336", 4)
}

enum class TaskCategory(val label: String, val colorHex: String) {
    WORK("Work", "#3F51B5"),
    PERSONAL("Personal", "#9C27B0"),
    FITNESS("Fitness", "#4CAF50"),
    STUDY("Study", "#00BCD4"),
    SHOPPING("Shopping", "#FF9800"),
    FINANCE("Finance", "#009688"),
    HEALTH("Health", "#E91E63"),
    OTHER("Other", "#607D8B")
}

enum class KanbanStatus(val label: String) {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    REVIEW("Under Review"),
    DONE("Completed")
}

data class Subtask(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val isCompleted: Boolean = false
)

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val category: String = TaskCategory.PERSONAL.label,
    val priority: String = TaskPriority.MEDIUM.name,
    val dueDate: Long? = null, // epoch millis
    val dueTime: String? = null, // e.g. "04:30 PM"
    val isCompleted: Boolean = false,
    val completedAt: Long? = null,
    val kanbanStatus: String = KanbanStatus.TODO.name,
    val subtasksJson: String = "[]",
    val isRecurring: Boolean = false,
    val repeatInterval: String = "NONE", // DAILY, WEEKLY, MONTHLY
    val reminderEnabled: Boolean = false,
    val estimatedMinutes: Int = 0,
    val tags: String = "",
    val createdAt: Long = System.currentTimeMillis()
) {
    fun getSubtaskList(): List<Subtask> {
        return try {
            val type = object : TypeToken<List<Subtask>>() {}.type
            Gson().fromJson(subtasksJson, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun isOverdue(): Boolean {
        if (isCompleted || dueDate == null) return false
        val now = System.currentTimeMillis()
        return dueDate < now
    }

    fun getPriorityEnum(): TaskPriority {
        return try {
            TaskPriority.valueOf(priority)
        } catch (e: Exception) {
            TaskPriority.MEDIUM
        }
    }
}
