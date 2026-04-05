# TaskFlow – Smart Task Manager

A personal productivity Android app built with **Clean Architecture**, **Jetpack Compose**, and modern Android development best practices. The app works fully offline with local data persistence and automated trash cleanup.

---

## Features

- **Task Management** — Create, view, edit, and delete tasks
- **Task Fields** — Title, Description, Category (Work/Personal/Shopping), Priority (High/Medium/Low), Due Date & Time, Status (Pending/In Progress/Completed)
- **Soft Delete & Trash** — Deleted tasks move to Trash instead of being permanently removed
- **Auto Cleanup** — Tasks in Trash for more than 7 days are automatically purged via WorkManager
- **Days Left Indicator** — Each trash item shows a color-coded badge (🟢 6–7d / 🟠 3–5d / 🔴 1–2d)
- **Search** — Real-time search across task titles and descriptions
- **Category Filter** — Filter tasks by Work, Personal, or Shopping
- **Restore / Permanent Delete** — Manually restore or permanently delete items from Trash
- **Bottom Navigation** — Switch between Tasks and Trash screens

---

## Tech Stack

| Technology | Purpose |
|---|---|
| **Jetpack Compose** | Declarative UI |
| **Room** | Local database with Flow support |
| **Hilt** | Dependency Injection |
| **WorkManager** | Background trash cleanup (runs daily) |
| **Navigation Compose** | Screen navigation with bottom bar |
| **Kotlin Coroutines + Flow** | Reactive data streams |
| **Material 3** | Design system and theming |
| **KSP** | Annotation processing for Room and Hilt |

---

## Architecture

The project follows **Clean Architecture** with clear separation of concerns across three layers:

```
com.taskflow/
├── data/
│   ├── local/          → Room DB, DAO, Entity, Mapper
│   ├── repository/     → Repository implementations
│   └── worker/         → WorkManager (TrashCleanupWorker)
├── domain/
│   ├── model/          → Task, TaskCategory, TaskPriority, TaskStatus
│   ├── repository/     → Repository interfaces
│   └── usecase/        → Business logic (one use case per action)
├── presentation/
│   ├── navigation/     → NavGraph, Screen routes
│   ├── ui/
│   │   ├── components/ → Reusable Compose components (TaskCard, badges)
│   │   ├── screens/    → TaskListScreen, CreateEditTaskScreen, TrashScreen
│   │   └── theme/      → Colors, Typography, Material3 theme
│   └── viewmodel/      → TaskListViewModel, TaskDetailViewModel, TrashViewModel
└── di/                 → Hilt modules (Database, Repository, UseCases)
```

### Key Design Decisions

- **Unidirectional Data Flow** — Each ViewModel exposes a single `UiState` via `StateFlow`. The UI only reads state and sends events upward.
- **Use Case per Action** — Each use case has a single responsibility, making them independently testable and reusable.
- **Mapper Pattern** — `TaskEntity` ↔ `Task` conversion is isolated in mapper files so data and domain layers never leak into each other.
- **Soft Delete** — Tasks are flagged `isDeleted = true` with a `deletedAt` timestamp. A daily WorkManager job automatically purges items older than 7 days.

---

## Setup Instructions

1. Clone the repository
   ```bash
   git clone https://github.com/your-username/taskflow.git
   ```
2. Open in **Android Studio Hedgehog (2023.1.1)** or later
3. Let Gradle sync complete
4. Run on a device or emulator with **API 26+**
5. No API keys or additional configuration required — the app is fully offline

---

## Requirements

- Android **8.0 (API 26)** minimum
- Compile SDK **35**
- Gradle **8.9**
- Android Gradle Plugin **8.7.3**
- Kotlin **2.0.0**

---

## Tools Used

- Android Studio
- Jetpack Compose
- Figma — UI design reference

---

## Known Limitations & Future Improvements

- [ ] Push notifications / reminders for due tasks
- [ ] Due date filters (Today / This Week / Overdue)
- [ ] Swipe-to-delete gesture on task cards
- [ ] Undo snackbar after soft delete
- [ ] Dark mode support
- [ ] Home screen widget
- [ ] Export tasks as CSV or JSON
- [ ] Multiple categories per task

---

## License

```
MIT License — free to use, modify, and distribute.
```
