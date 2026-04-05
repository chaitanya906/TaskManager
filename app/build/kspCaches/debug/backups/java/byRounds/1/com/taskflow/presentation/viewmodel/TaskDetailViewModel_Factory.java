package com.taskflow.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
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
public final class TaskDetailViewModel_Factory implements Factory<TaskDetailViewModel> {
  private final Provider<TaskUseCases> useCasesProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public TaskDetailViewModel_Factory(Provider<TaskUseCases> useCasesProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.useCasesProvider = useCasesProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public TaskDetailViewModel get() {
    return newInstance(useCasesProvider.get(), savedStateHandleProvider.get());
  }

  public static TaskDetailViewModel_Factory create(Provider<TaskUseCases> useCasesProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new TaskDetailViewModel_Factory(useCasesProvider, savedStateHandleProvider);
  }

  public static TaskDetailViewModel newInstance(TaskUseCases useCases,
      SavedStateHandle savedStateHandle) {
    return new TaskDetailViewModel(useCases, savedStateHandle);
  }
}
