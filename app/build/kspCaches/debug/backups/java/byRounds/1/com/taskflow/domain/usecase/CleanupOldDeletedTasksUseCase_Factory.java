package com.taskflow.domain.usecase;

import com.taskflow.domain.repository.TaskRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class CleanupOldDeletedTasksUseCase_Factory implements Factory<CleanupOldDeletedTasksUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public CleanupOldDeletedTasksUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public CleanupOldDeletedTasksUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static CleanupOldDeletedTasksUseCase_Factory create(
      Provider<TaskRepository> repoProvider) {
    return new CleanupOldDeletedTasksUseCase_Factory(repoProvider);
  }

  public static CleanupOldDeletedTasksUseCase newInstance(TaskRepository repo) {
    return new CleanupOldDeletedTasksUseCase(repo);
  }
}
