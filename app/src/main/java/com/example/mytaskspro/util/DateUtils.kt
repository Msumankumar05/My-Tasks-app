package com.example.mytaskspro.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

    fun formatDate(epochMillis: Long?): String {
        if (epochMillis == null) return "No due date"
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        val currentYear = calendar.get(Calendar.YEAR)

        val taskCalendar = Calendar.getInstance().apply { timeInMillis = epochMillis }
        val taskDay = taskCalendar.get(Calendar.DAY_OF_YEAR)
        val taskYear = taskCalendar.get(Calendar.YEAR)

        return when {
            taskYear == currentYear && taskDay == today -> "Today"
            taskYear == currentYear && taskDay == today + 1 -> "Tomorrow"
            taskYear == currentYear && taskDay == today - 1 -> "Yesterday"
            else -> dateFormatter.format(Date(epochMillis))
        }
    }

    fun formatTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        return timeFormatter.format(calendar.time)
    }

    fun combineDateAndTime(dateMillis: Long, hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateMillis
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.timeInMillis
    }
}
