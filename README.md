<div align="center">

<!-- Header -->
<img src="https://img.shields.io/badge/✅-My_Tasks_Pro_v2.0-1a1a2e?style=for-the-badge&labelColor=16213e&color=0f3460" alt="My Tasks Pro" height="40">

# 📱 My Tasks Pro — Version 2.0 (Smart Productivity Suite)

**Organize. Focus. Excel.**

A beautifully crafted, feature-packed native Android task management suite — built with Kotlin, Jetpack Compose, Material 3, and Room Database.

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](#-device-requirements)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](#-tech-stack)
[![Compose](https://img.shields.io/badge/UI-Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](#-tech-stack)
[![API](https://img.shields.io/badge/Min_API-24+-brightgreen?style=for-the-badge&logo=android&logoColor=white)](#-device-requirements)
[![Version](https://img.shields.io/badge/Version-2.0--PRO-0078D4?style=for-the-badge)](#-changelog)
[![License](https://img.shields.io/badge/License-MIT-F7DF1E?style=for-the-badge)](LICENSE)

---

### 📥 Download APK (v2.0-PRO)

<a href="https://github.com/Msumankumar05/My-Tasks-app/releases/latest">
  <img src="https://img.shields.io/badge/⬇️_Download_APK-v2.0--PRO_•_18.2_MB-00C853?style=for-the-badge&logoColor=white" alt="Download APK" height="55">
</a>

<br>
<sub>✅ 100% Offline • 🔒 Zero Data Tracking • 📵 No Ads • 🆓 100% Free & Open Source</sub>

---

### 🖼️ App Preview & Screenshots

<img src="images/_3956_Screenshot_2026-04-17-11-32-20-40_0c1cf1fd2f8ceaa9f0b42fd73d9d0063__620_Screenshot_2026-04-15-00-43-_.-15-00-43-15-84_0c1cf1fd2f8ceaa9f0b42fd73d9d0063__3619_Screenshot_2026-04-15-00-43-05-63_0c1.jpeg" alt="My Tasks App Screenshots" width="800">

<sub><i>Tasks List • Calendar View • Focus Pomodoro • Custom Themes</i></sub>

</div>

---

## ✨ Features & Capabilities

| Feature | Description |
|:--------|:------------|
| 📋 **Task Management** | Full CRUD operations, categories with color tags, priorities (`LOW`, `MEDIUM`, `HIGH`, `URGENT`), and subtask checklists. |
| 📊 **Kanban Board** | Interactive columns (`To Do`, `In Progress`, `Under Review`, `Completed`). |
| 📅 **Calendar View** | Interactive monthly grid view showing scheduled task deadlines per date. |
| ⏱️ **Focus Pomodoro Timer** | Built-in Pomodoro timer with Work (25 min), Short Break (5 min), and Long Break (15 min) cycles. |
| 📈 **Productivity Analytics** | Productivity score %, active day streak counter, completion rate charts, and stat cards. |
| 🎨 **7 Color Themes** | System, Light, Dark, OLED Pitch Black, Cyberpunk Neon, Sunset Glow, and Emerald Forest. |
| ⚡ **Natural Language Quick Add** | Smart quick task entry (e.g. `Client Meeting #Work !High`). |
| 💾 **Data Backup & Restore** | Export and copy complete JSON data backups with zero cloud tracking. |
| ⏰ **Exact Push Reminders** | Scheduled notifications via `AlarmManager` with direct action buttons (`Mark Done`). |

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin 2.0
- **UI Framework**: Jetpack Compose + Material 3
- **Architecture Pattern**: MVVM (`ViewModel`, `StateFlow`, `Repository`, `DAO`)
- **Database**: Room Database (SQLite)
- **Notifications**: Android `AlarmManager` + `NotificationCompat`
- **JSON Engine**: Gson
- **Min SDK**: 24 (Android 7.0 Nougat) | **Target SDK**: 36 (Android 15)

---

## 📂 Source Code Structure

```
My-Tasks-app/
├── build.gradle.kts (Project build configuration)
├── settings.gradle.kts (Settings configuration)
├── gradle.properties
├── gradlew & gradlew.bat
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       └── java/com/example/mytaskspro/
│           ├── MainActivity.kt
│           ├── MyTasksApplication.kt
│           ├── data/ (Task, TaskDao, AppDatabase, TaskRepository)
│           ├── notification/ (NotificationHelper, ReminderReceiver)
│           ├── ui/ (MainScreen, theme, viewmodel, components, screens)
│           └── util/ (DateUtils, DataExportImport)
├── apk/ (Precompiled APK binaries)
└── images/ (App screenshots)
```

---

## 🚀 Building & Running

```bash
# Clone repository
git clone https://github.com/Msumankumar05/My-Tasks-app.git
cd My-Tasks-app

# Build debug APK using Gradle Wrapper
./gradlew assembleDebug
```

---

## 📄 License

Distributed under the MIT License. See [LICENSE](LICENSE) for details.
