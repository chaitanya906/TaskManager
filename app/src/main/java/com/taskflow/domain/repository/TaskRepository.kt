package com.taskflow.domain.repository
import com.taskflow.domain.model.*
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllActiveTasks(): Flow<List<Task>>
    fun getDeletedTasks(): Flow<List<Task>>
    fun searchTasks(query: String): Flow<List<Task>>
    fun getTasksByCategory(category: TaskCategory): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun softDeleteTask(id: Long)
    suspend fun restoreTask(id: Long)
    suspend fun permanentlyDeleteTask(id: Long)
    suspend fun deleteTasksOlderThan(thresholdMillis: Long)
}
