package com.taskflow.presentation.viewmodel;

import com.taskflow.domain.usecase.TaskUseCases;
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
public final class TaskListViewModel_Factory implements Factory<TaskListViewModel> {
  private final Provider<TaskUseCases> useCasesProvider;

  public TaskListViewModel_Factory(Provider<TaskUseCases> useCasesProvider) {
    this.useCasesProvider = useCasesProvider;
  }

  @Override
  public TaskListViewModel get() {
    return newInstance(useCasesProvider.get());
  }

  public static TaskListViewModel_Factory create(Provider<TaskUseCases> useCasesProvider) {
    return new TaskListViewModel_Factory(useCasesProvider);
  }

  public static TaskListViewModel newInstance(TaskUseCases useCases) {
    return new TaskListViewModel(useCases);
  }
}
