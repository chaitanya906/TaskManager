package com.taskflow.data.repository
import com.taskflow.data.local.dao.TaskDao
import com.taskflow.data.local.mapper.*
import com.taskflow.domain.model.*
import com.taskflow.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) : TaskRepository {
    override fun getAllActiveTasks() = dao.getAllActiveTasks().map { it.map { e -> e.toDomain() } }
    override fun getDeletedTasks() = dao.getDeletedTasks().map { it.map { e -> e.toDomain() } }
    override fun searchTasks(query: String) = dao.searchTasks(query).map { it.map { e -> e.toDomain() } }
    override fun getTasksByCategory(category: TaskCategory) = dao.getTasksByCategory(category.name).map { it.map { e -> e.toDomain() } }
    override suspend fun getTaskById(id: Long) = dao.getTaskById(id)?.toDomain()
    override suspend fun insertTask(task: Task) = dao.insertTask(task.toEntity())
    override suspend fun updateTask(task: Task) = dao.updateTask(task.toEntity())
    override suspend fun softDeleteTask(id: Long) = dao.softDeleteTask(id, System.currentTimeMillis())
    override suspend fun restoreTask(id: Long) = dao.restoreTask(id)
    override suspend fun permanentlyDeleteTask(id: Long) = dao.permanentlyDeleteTask(id)
    override suspend fun deleteTasksOlderThan(thresholdMillis: Long) = dao.deleteTasksOlderThan(thresholdMillis)
}
