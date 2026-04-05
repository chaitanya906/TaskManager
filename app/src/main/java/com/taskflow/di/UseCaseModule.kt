package com.taskflow.di
import com.taskflow.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides @Singleton
    fun provideTaskUseCases(
        getActiveTasks: GetActiveTasksUseCase,
        getDeletedTasks: GetDeletedTasksUseCase,
        getTaskById: GetTaskByIdUseCase,
        createTask: CreateTaskUseCase,
        updateTask: UpdateTaskUseCase,
        softDeleteTask: SoftDeleteTaskUseCase,
        restoreTask: RestoreTaskUseCase,
        permanentlyDeleteTask: PermanentlyDeleteTaskUseCase,
        cleanupOldDeletedTasks: CleanupOldDeletedTasksUseCase
    ) = TaskUseCases(getActiveTasks, getDeletedTasks, getTaskById, createTask,
        updateTask, softDeleteTask, restoreTask, permanentlyDeleteTask, cleanupOldDeletedTasks)
}
