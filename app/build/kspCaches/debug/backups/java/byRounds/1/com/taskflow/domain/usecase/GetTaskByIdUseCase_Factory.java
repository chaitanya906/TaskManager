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
public final class GetTaskByIdUseCase_Factory implements Factory<GetTaskByIdUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public GetTaskByIdUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetTaskByIdUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static GetTaskByIdUseCase_Factory create(Provider<TaskRepository> repoProvider) {
    return new GetTaskByIdUseCase_Factory(repoProvider);
  }

  public static GetTaskByIdUseCase newInstance(TaskRepository repo) {
    return new GetTaskByIdUseCase(repo);
  }
}
