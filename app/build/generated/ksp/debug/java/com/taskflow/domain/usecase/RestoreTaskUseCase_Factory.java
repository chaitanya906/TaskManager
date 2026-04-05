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
public final class RestoreTaskUseCase_Factory implements Factory<RestoreTaskUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public RestoreTaskUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public RestoreTaskUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static RestoreTaskUseCase_Factory create(Provider<TaskRepository> repoProvider) {
    return new RestoreTaskUseCase_Factory(repoProvider);
  }

  public static RestoreTaskUseCase newInstance(TaskRepository repo) {
    return new RestoreTaskUseCase(repo);
  }
}
