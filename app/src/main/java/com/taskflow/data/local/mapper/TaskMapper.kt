package com.taskflow.data.local.mapper
import com.taskflow.data.local.entity.TaskEntity
import com.taskflow.domain.model.*
import java.time.*

fun TaskEntity.toDomain() = Task(
    id=id, title=title, description=description,
    category=TaskCategory.valueOf(category),
    priority=TaskPriority.valueOf(priority),
    dueDate=dueDate?.toLocalDateTime(),
    status=TaskStatus.valueOf(status),
    createdAt=createdAt.toLocalDateTime(),
    lastModifiedAt=lastModifiedAt.toLocalDateTime(),
    isDeleted=isDeleted,
    deletedAt=deletedAt?.toLocalDateTime()
)

fun Task.toEntity() = TaskEntity(
    id=id, title=title, description=description,
    category=category.name, priority=priority.name,
    dueDate=dueDate?.toEpoch(), status=status.name,
    createdAt=createdAt.toEpoch(), lastModifiedAt=lastModifiedAt.toEpoch(),
    isDeleted=isDeleted, deletedAt=deletedAt?.toEpoch()
)

private fun Long.toLocalDateTime(): LocalDateTime =
    LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
private fun LocalDateTime.toEpoch(): Long =
    atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
