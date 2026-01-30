# TaskManagerAndroidApp_Kotlin

# Features

- View list of tasks
- View task details
- Create a new task
- Capture an image using device camera
- Attach current location to a task
- Runtime permission handling (Camera & Location)
- Dark mode support (Add Different Color for Day and Night in Values)
- Loading and error state handling

---

# Architecture

The app follows MVVM architecture with a clean separation of concerns.


---

# Tech Stack & Libraries

- Language: Kotlin
- Architecture: MVVM
- Dependency Injection: Hilt
- Navigation: Jetpack Navigation Component
- UI: XML + Material Design
- View Binding: Enabled
- Location: FusedLocationProviderClient
- mage Capture: ActivityResultContracts
- Min SDK: 28
- Target SDK: Latest stable

---

# Permissions Handling

- Camera and Location permissions are handled using `ActivityResultContracts`
- Permission rationale and denial cases are handled gracefully
- App does not crash if permissions are denied

---

# Dark Mode

- Supports system-wide dark mode
- Implemented using Material DayNight theme

---

# Testing

- Tested on Android 9 (API 28)
- Tested on Android 15 (latest)

---

# Notes

- UI design is kept simple to focus on architecture and code quality
- All task data is mocked and stored in memory
- Emphasis on readability, maintainability, and Android best practices

---

---

# How to Run
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator or real device

# Developed by

Mudassir Khan
Android Developer




