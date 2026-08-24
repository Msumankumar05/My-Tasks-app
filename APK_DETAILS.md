<div align="center">

# 📦 My Tasks Pro APK (v2.0) — Technical Specifications & Guide

[![Version](https://img.shields.io/badge/Version-2.0--PRO-0078D4?style=for-the-badge)](#)
[![Android](https://img.shields.io/badge/Min_Android-7.0_Nougat-3DDC84?style=for-the-badge&logo=android&logoColor=white)](#)
[![Status](https://img.shields.io/badge/Status-✅_Stable-00C853?style=for-the-badge)](#)

</div>

---

## 📊 Technical Specifications (v2.0-PRO)

| Property | Details |
|:---------|:--------|
| **📦 Package Name** | `com.example.mytaskspro` |
| **🏷️ Version Name** | 2.0-PRO |
| **🔢 Version Code** | 2 |
| **📱 Minimum SDK** | 24 (Android 7.0 Nougat) |
| **🎯 Target SDK** | 36 (Android 15) |
| **🎨 UI Framework** | Jetpack Compose + Material 3 |
| **💾 Local Database** | Room Database (SQLite) |
| **⏰ Reminders** | AlarmManager + NotificationCompat |
| **🎨 Themes** | 7 Modes (Dark, Light, OLED, Cyberpunk, Sunset, Forest, System) |

---

## ✨ Features Included in v2.0-PRO

- ✅ Multi-view navigation (Tasks, Kanban Board, Calendar, Focus Pomodoro, Analytics, Settings)
- ✅ Priority levels (`LOW`, `MEDIUM`, `HIGH`, `URGENT`) & Category pills
- ✅ Subtask checklists with real-time progress indicators
- ✅ Natural language quick-add input syntax (`#Category !Priority`)
- ✅ Focus Pomodoro Timer with Work / Break cycles
- ✅ Productivity Dashboard (Streak counter, score %, completed/pending breakdown)
- ✅ Local JSON/CSV Data Backup & Copy to Clipboard
- ✅ Overdue detection & Exact alarm notifications

---

## 📲 How to Build APK

```bash
# Build Debug APK
./gradlew assembleDebug

# Output APK path:
# app/build/outputs/apk/debug/app-debug.apk
```
