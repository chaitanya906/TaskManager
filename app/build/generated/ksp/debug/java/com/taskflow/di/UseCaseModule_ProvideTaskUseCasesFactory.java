package com.taskflow.di;

import com.taskflow.domain.usecase.CleanupOldDeletedTasksUseCase;
import com.taskflow.domain.usecase.CreateTaskUseCase;
import com.taskflow.domain.usecase.GetActiveTasksUseCase;
import com.taskflow.domain.usecase.GetDeletedTasksUseCase;
import com.taskflow.domain.usecase.GetTaskByIdUseCase;
import com.taskflow.domain.usecase.PermanentlyDeleteTaskUseCase;
import com.taskflow.domain.usecase.RestoreTaskUseCase;
import com.taskflow.domain.usecase.SoftDeleteTaskUseCase;
import com.taskflow.domain.usecase.TaskUseCases;
import com.taskflow.domain.usecase.UpdateTaskUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class UseCaseModule_ProvideTaskUseCasesFactory implements Factory<TaskUseCases> {
  private final Provider<GetActiveTasksUseCase> getActiveTasksProvider;

  private final Provider<GetDeletedTasksUseCase> getDeletedTasksProvider;

  private final Provider<GetTaskByIdUseCase> getTaskByIdProvider;

  private final Provider<CreateTaskUseCase> createTaskProvider;

  private final Provider<UpdateTaskUseCase> updateTaskProvider;

  private final Provider<SoftDeleteTaskUseCase> softDeleteTaskProvider;

  private final Provider<RestoreTaskUseCase> restoreTaskProvider;

  private final Provider<PermanentlyDeleteTaskUseCase> permanentlyDeleteTaskProvider;

  private final Provider<CleanupOldDeletedTasksUseCase> cleanupOldDeletedTasksProvider;

  public UseCaseModule_ProvideTaskUseCasesFactory(
      Provider<GetActiveTasksUseCase> getActiveTasksProvider,
      Provider<GetDeletedTasksUseCase> getDeletedTasksProvider,
      Provider<GetTaskByIdUseCase> getTaskByIdProvider,
      Provider<CreateTaskUseCase> createTaskProvider,
      Provider<UpdateTaskUseCase> updateTaskProvider,
      Provider<SoftDeleteTaskUseCase> softDeleteTaskProvider,
      Provider<RestoreTaskUseCase> restoreTaskProvider,
      Provider<PermanentlyDeleteTaskUseCase> permanentlyDeleteTaskProvider,
      Provider<CleanupOldDeletedTasksUseCase> cleanupOldDeletedTasksProvider) {
    this.getActiveTasksProvider = getActiveTasksProvider;
    this.getDeletedTasksProvider = getDeletedTasksProvider;
    this.getTaskByIdProvider = getTaskByIdProvider;
    this.createTaskProvider = createTaskProvider;
    this.updateTaskProvider = updateTaskProvider;
    this.softDeleteTaskProvider = softDeleteTaskProvider;
    this.restoreTaskProvider = restoreTaskProvider;
    this.permanentlyDeleteTaskProvider = permanentlyDeleteTaskProvider;
    this.cleanupOldDeletedTasksProvider = cleanupOldDeletedTasksProvider;
  }

  @Override
  public TaskUseCases get() {
    return provideTaskUseCases(getActiveTasksProvider.get(), getDeletedTasksProvider.get(), getTaskByIdProvider.get(), createTaskProvider.get(), updateTaskProvider.get(), softDeleteTaskProvider.get(), restoreTaskProvider.get(), permanentlyDeleteTaskProvider.get(), cleanupOldDeletedTasksProvider.get());
  }

  public static UseCaseModule_ProvideTaskUseCasesFactory create(
      Provider<GetActiveTasksUseCase> getActiveTasksProvider,
      Provider<GetDeletedTasksUseCase> getDeletedTasksProvider,
      Provider<GetTaskByIdUseCase> getTaskByIdProvider,
      Provider<CreateTaskUseCase> createTaskProvider,
      Provider<UpdateTaskUseCase> updateTaskProvider,
      Provider<SoftDeleteTaskUseCase> softDeleteTaskProvider,
      Provider<RestoreTaskUseCase> restoreTaskProvider,
      Provider<PermanentlyDeleteTaskUseCase> permanentlyDeleteTaskProvider,
      Provider<CleanupOldDeletedTasksUseCase> cleanupOldDeletedTasksProvider) {
    return new UseCaseModule_ProvideTaskUseCasesFactory(getActiveTasksProvider, getDeletedTasksProvider, getTaskByIdProvider, createTaskProvider, updateTaskProvider, softDeleteTaskProvider, restoreTaskProvider, permanentlyDeleteTaskProvider, cleanupOldDeletedTasksProvider);
  }

  public static TaskUseCases provideTaskUseCases(GetActiveTasksUseCase getActiveTasks,
      GetDeletedTasksUseCase getDeletedTasks, GetTaskByIdUseCase getTaskById,
      CreateTaskUseCase createTask, UpdateTaskUseCase updateTask,
      SoftDeleteTaskUseCase softDeleteTask, RestoreTaskUseCase restoreTask,
      PermanentlyDeleteTaskUseCase permanentlyDeleteTask,
      CleanupOldDeletedTasksUseCase cleanupOldDeletedTasks) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideTaskUseCases(getActiveTasks, getDeletedTasks, getTaskById, createTask, updateTask, softDeleteTask, restoreTask, permanentlyDeleteTask, cleanupOldDeletedTasks));
  }
}
