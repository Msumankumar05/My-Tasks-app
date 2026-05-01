# 🤝 Contributing to My Tasks

First off, thank you for considering contributing to **My Tasks**! Every contribution helps make this app better for everyone. 🎉

---

## 📋 Table of Contents

- [Code of Conduct](#-code-of-conduct)
- [How Can I Contribute?](#-how-can-i-contribute)
- [Reporting Bugs](#-reporting-bugs)
- [Suggesting Features](#-suggesting-features)
- [Pull Requests](#-pull-requests)
- [Development Setup](#-development-setup)
- [Style Guidelines](#-style-guidelines)

---

## 📜 Code of Conduct

This project adheres to a simple code of conduct: **be respectful, be constructive, and be kind**. We're all here to build something great together.

---

## 🙋 How Can I Contribute?

### 🐛 Reporting Bugs

Found a bug? Help us fix it!

1. **Check existing issues** — make sure it hasn't been reported already
2. **Open a new issue** with a clear title and description
3. Include:
   - Steps to reproduce the bug
   - Expected vs actual behavior
   - Device model and Android version
   - Screenshots or screen recordings (if applicable)

### 💡 Suggesting Features

Have an idea? We'd love to hear it!

1. **Open a new issue** with the `enhancement` label
2. Describe:
   - The feature you'd like to see
   - Why it would be useful
   - How it might work (optional)

### 🔧 Pull Requests

Want to contribute code? Awesome!

1. **Fork** the repository
2. **Create a branch** for your feature (`git checkout -b feature/amazing-feature`)
3. **Make your changes** following our [style guidelines](#-style-guidelines)
4. **Test** your changes thoroughly
5. **Commit** with clear, descriptive messages
6. **Push** to your branch (`git push origin feature/amazing-feature`)
7. **Open a Pull Request** with a clear description of your changes

---

## 🛠️ Development Setup

### Prerequisites

- **Android Studio** Hedgehog (2023.1) or newer
- **JDK 17** or higher
- **Android SDK** with API 36 installed
- **Kotlin** 1.9+

### Getting Started

```bash
# 1. Fork and clone the repository
git clone https://github.com/YOUR_USERNAME/My-Tasks-app.git

# 2. Open the project in Android Studio

# 3. Sync Gradle and build the project

# 4. Run on an emulator or physical device (API 24+)
```

### Project Structure

```
My-Tasks-app/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/to_dolistapp/
│   │   │   ├── data/          # Room Database, DAOs, Entities
│   │   │   ├── ui/            # Jetpack Compose Screens & Components
│   │   │   ├── viewmodel/     # ViewModels (MVVM)
│   │   │   └── utils/         # Helper classes & utilities
│   │   └── res/               # Resources (drawables, strings, etc.)
│   └── build.gradle.kts
├── apk/                       # Pre-built APK for download
├── images/                    # Screenshots for README
└── README.md
```

---

## 🎨 Style Guidelines

### Kotlin

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add KDoc comments for public functions and classes
- Keep functions small and focused

### Jetpack Compose

- Use `@Preview` annotations for composable functions
- Follow Material 3 design guidelines
- Keep composables stateless where possible
- Extract reusable components

### Git Commits

- Use clear, descriptive commit messages
- Follow the format: `type: short description`
  - `feat:` — New feature
  - `fix:` — Bug fix
  - `docs:` — Documentation changes
  - `style:` — Code formatting (no logic change)
  - `refactor:` — Code restructuring
  - `test:` — Adding/updating tests

**Example:**
```
feat: add swipe-to-delete for tasks
fix: reminder notification not firing on Android 13+
docs: update installation instructions
```

---

## 📬 Questions?

Feel free to reach out:

- 📧 **Email:** [ms.kumar.developer05@gmail.com](mailto:ms.kumar.developer05@gmail.com)
- 🐙 **GitHub Issues:** [Open an issue](https://github.com/Msumankumar05/My-Tasks-app/issues)

---

**Thank you for contributing! 🙏**
