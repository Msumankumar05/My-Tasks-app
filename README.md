<div align="center">

<!-- Header -->
<img src="https://img.shields.io/badge/✅-My_Tasks_Pro_v2.0-1a1a2e?style=for-the-badge&labelColor=16213e&color=0f3460" alt="My Tasks Pro" height="40">

# 📱 My Tasks Pro — Version 2.0 (Smart Productivity Suite)

**Organize. Focus. Excel.**

A beautifully crafted, feature-packed Android task management suite — built with Jetpack Compose, Material 3, and Room Database.

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](#-device-requirements)
[![API](https://img.shields.io/badge/Min_API-24+-brightgreen?style=for-the-badge&logo=android&logoColor=white)](#-device-requirements)
[![Version](https://img.shields.io/badge/Version-2.0--PRO-0078D4?style=for-the-badge)](#-changelog)
[![License](https://img.shields.io/badge/License-MIT-F7DF1E?style=for-the-badge)](LICENSE)
[![Stars](https://img.shields.io/github/stars/Msumankumar05/My-Tasks-app?style=for-the-badge&logo=github&color=yellow)](https://github.com/Msumankumar05/My-Tasks-app/stargazers)

---

### 📥 Download v2.0-PRO

<a href="https://github.com/Msumankumar05/My-Tasks-app/releases/latest">
  <img src="https://img.shields.io/badge/⬇️_Download_APK-v2.0--PRO_•_18.2_MB-00C853?style=for-the-badge&logoColor=white" alt="Download APK" height="55">
</a>

<br>
<sub>✅ 100% Offline • 🔒 Zero Data Tracking • 📵 No Ads • 🆓 Free & Open Source</sub>

</div>

---

## 🚀 What's New in Version 2.0 PRO

| Feature | Description |
|:--------|:------------|
| 📋 **Task Management** | Grouped task lists (Overdue, Pending, Completed) with priorities (`LOW`, `MEDIUM`, `HIGH`, `URGENT`) and subtask checklists. |
| 📊 **Kanban Board** | Drag & drop interactive board columns (`To Do`, `In Progress`, `Under Review`, `Completed`). |
| 📅 **Calendar View** | Interactive monthly grid view showing scheduled task deadlines per date. |
| ⏱️ **Focus Pomodoro Timer** | Work (25 min), Short Break (5 min), and Long Break (15 min) timer cycles. |
| 📈 **Productivity Analytics** | Productivity score %, active day streak counter, completion rate charts, and stat cards. |
| 🎨 **Theme Customization** | 7 Color Themes: System, Light, Dark, OLED Pitch Black, Cyberpunk Neon, Sunset Glow, and Emerald Forest. |
| ⚡ **Natural Language Quick Add** | Smart quick task entry (e.g. `Client Meeting #Work !High`). |
| 💾 **Data Backup & Restore** | Export and copy complete JSON data backups. |
| ⏰ **Exact Push Reminders** | Scheduled notifications via `AlarmManager` with direct action buttons (`Mark Done`). |

---

## 🛠️ Architecture & Tech Stack

- **Language**: Kotlin 2.0
- **UI Framework**: Jetpack Compose + Material 3
- **Architecture**: MVVM (`ViewModel`, `StateFlow`, `Repository`, `DAO`)
- **Database**: Room Database (SQLite)
- **Notifications**: Android `AlarmManager` + `NotificationCompat`
- **JSON Engine**: Gson
- **Min SDK**: 24 (Android 7.0 Nougat) | **Target SDK**: 36 (Android 15)

---

## 📂 Source Code & Building

```bash
# Clone the repository
git clone https://github.com/Msumankumar05/My-Tasks-app.git
cd My-Tasks-app

# Build debug APK using Gradle Wrapper
./gradlew assembleDebug
```

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
