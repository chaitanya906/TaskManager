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
public final class SoftDeleteTaskUseCase_Factory implements Factory<SoftDeleteTaskUseCase> {
  private final Provider<TaskRepository> repoProvider;

  public SoftDeleteTaskUseCase_Factory(Provider<TaskRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public SoftDeleteTaskUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static SoftDeleteTaskUseCase_Factory create(Provider<TaskRepository> repoProvider) {
    return new SoftDeleteTaskUseCase_Factory(repoProvider);
  }

  public static SoftDeleteTaskUseCase newInstance(TaskRepository repo) {
    return new SoftDeleteTaskUseCase(repo);
  }
}
