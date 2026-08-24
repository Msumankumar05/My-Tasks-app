package com.example.mytaskspro.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
    val pendingTasks: Flow<List<Task>> = taskDao.getPendingTasks()
    val completedTasks: Flow<List<Task>> = taskDao.getCompletedTasks()
    val completedCount: Flow<Int> = taskDao.getCompletedCount()
    val pendingCount: Flow<Int> = taskDao.getPendingCount()

    fun getTasksByKanban(status: String): Flow<List<Task>> = taskDao.getTasksByKanbanStatus(status)

    fun searchTasks(query: String): Flow<List<Task>> = taskDao.searchTasks(query)

    suspend fun getTaskById(id: Long): Task? = taskDao.getTaskById(id)

    suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun clearCompleted() = taskDao.clearCompletedTasks()

    suspend fun toggleTaskCompletion(task: Task) {
        val updatedStatus = if (!task.isCompleted) KanbanStatus.DONE.name else KanbanStatus.TODO.name
        val updatedTask = task.copy(
            isCompleted = !task.isCompleted,
            completedAt = if (!task.isCompleted) System.currentTimeMillis() else null,
            kanbanStatus = updatedStatus
        )
        taskDao.updateTask(updatedTask)
    }

    suspend fun updateKanbanStatus(task: Task, newStatus: String) {
        val isDone = newStatus == KanbanStatus.DONE.name
        val updatedTask = task.copy(
            kanbanStatus = newStatus,
            isCompleted = isDone,
            completedAt = if (isDone) System.currentTimeMillis() else task.completedAt
        )
        taskDao.updateTask(updatedTask)
    }
}
