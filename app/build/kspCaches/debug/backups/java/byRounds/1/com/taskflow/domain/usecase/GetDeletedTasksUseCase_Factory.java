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
public final class GetDeletedTasksUseCase_Factory implements Factory<GetDeletedTasksUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public GetDeletedTasksUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetDeletedTasksUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static GetDeletedTasksUseCase_Factory create(Provider<TaskRepository> repoProvider) {
    return new GetDeletedTasksUseCase_Factory(repoProvider);
  }

  public static GetDeletedTasksUseCase newInstance(TaskRepository repo) {
    return new GetDeletedTasksUseCase(repo);
  }
}
