package com.example.mytaskspro

import android.app.Application
import com.example.mytaskspro.data.AppDatabase

class MyTasksApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
