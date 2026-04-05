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
public final class UpdateTaskUseCase_Factory implements Factory<UpdateTaskUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public UpdateTaskUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public UpdateTaskUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static UpdateTaskUseCase_Factory create(Provider<TaskRepository> repoProvider) {
    return new UpdateTaskUseCase_Factory(repoProvider);
  }

  public static UpdateTaskUseCase newInstance(TaskRepository repo) {
    return new UpdateTaskUseCase(repo);
  }
}
