package com.taskflow.domain.model
import java.time.LocalDateTime

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val category: TaskCategory = TaskCategory.PERSONAL,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val dueDate: LocalDateTime? = null,
    val status: TaskStatus = TaskStatus.PENDING,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val lastModifiedAt: LocalDateTime = LocalDateTime.now(),
    val isDeleted: Boolean = false,
    val deletedAt: LocalDateTime? = null
)

enum class TaskCategory(val displayName: String) {
    WORK("Work"), PERSONAL("Personal"), SHOPPING("Shopping")
}

enum class TaskPriority(val displayName: String, val order: Int) {
    HIGH("High", 0), MEDIUM("Medium", 1), LOW("Low", 2)
}

enum class TaskStatus(val displayName: String) {
    PENDING("Pending"), IN_PROGRESS("In Progress"), COMPLETED("Completed")
}
