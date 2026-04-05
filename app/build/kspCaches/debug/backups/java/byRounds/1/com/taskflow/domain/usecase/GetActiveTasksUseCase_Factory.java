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
public final class GetActiveTasksUseCase_Factory implements Factory<GetActiveTasksUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public GetActiveTasksUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetActiveTasksUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static GetActiveTasksUseCase_Factory create(Provider<TaskRepository> repoProvider) {
    return new GetActiveTasksUseCase_Factory(repoProvider);
  }

  public static GetActiveTasksUseCase newInstance(TaskRepository repo) {
    return new GetActiveTasksUseCase(repo);
  }
}
