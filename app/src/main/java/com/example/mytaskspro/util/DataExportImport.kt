package com.example.mytaskspro.util

import com.example.mytaskspro.data.Task
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object DataExportImport {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun exportToJson(tasks: List<Task>): String {
        return gson.toJson(tasks)
    }

    fun importFromJson(jsonString: String): List<Task> {
        return try {
            val type = object : TypeToken<List<Task>>() {}.type
            gson.fromJson(jsonString, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun exportToCsv(tasks: List<Task>): String {
        val sb = java.lang.StringBuilder()
        sb.append("ID,Title,Description,Category,Priority,DueDate,DueTime,IsCompleted,KanbanStatus\n")
        for (t in tasks) {
            val escapedTitle = t.title.replace("\"", "\"\"")
            val escapedDesc = t.description.replace("\"", "\"\"")
            sb.append("${t.id},\"$escapedTitle\",\"$escapedDesc\",${t.category},${t.priority},${t.dueDate ?: ""},${t.dueTime ?: ""},${t.isCompleted},${t.kanbanStatus}\n")
        }
        return sb.toString()
    }
}
