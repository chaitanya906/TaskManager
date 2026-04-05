package com.taskflow.domain.usecase
import com.taskflow.domain.model.*
import com.taskflow.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActiveTasksUseCase @Inject constructor(private val repo: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = repo.getAllActiveTasks()
}
class GetDeletedTasksUseCase @Inject constructor(private val repo: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = repo.getDeletedTasks()
}
class GetTaskByIdUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(id: Long): Task? = repo.getTaskById(id)
}
class CreateTaskUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(task: Task): Long = repo.insertTask(task)
}
class UpdateTaskUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(task: Task) = repo.updateTask(task)
}
class SoftDeleteTaskUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(id: Long) = repo.softDeleteTask(id)
}
class RestoreTaskUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(id: Long) = repo.restoreTask(id)
}
class PermanentlyDeleteTaskUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke(id: Long) = repo.permanentlyDeleteTask(id)
}
class CleanupOldDeletedTasksUseCase @Inject constructor(private val repo: TaskRepository) {
    suspend operator fun invoke() {
        repo.deleteTasksOlderThan(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000L)
    }
}

data class TaskUseCases(
    val getActiveTasks: GetActiveTasksUseCase,
    val getDeletedTasks: GetDeletedTasksUseCase,
    val getTaskById: GetTaskByIdUseCase,
    val createTask: CreateTaskUseCase,
    val updateTask: UpdateTaskUseCase,
    val softDeleteTask: SoftDeleteTaskUseCase,
    val restoreTask: RestoreTaskUseCase,
    val permanentlyDeleteTask: PermanentlyDeleteTaskUseCase,
    val cleanupOldDeletedTasks: CleanupOldDeletedTasksUseCase
)
