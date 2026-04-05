package com.taskflow.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val category: String,
    val priority: String,
    val dueDate: Long?,
    val status: String,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val isDeleted: Boolean,
    val deletedAt: Long?
)
