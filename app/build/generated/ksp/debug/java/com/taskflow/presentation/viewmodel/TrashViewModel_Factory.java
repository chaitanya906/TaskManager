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
public final class TrashViewModel_Factory implements Factory<TrashViewModel> {
  private final Provider<TaskUseCases> useCasesProvider;

  public TrashViewModel_Factory(Provider<TaskUseCases> useCasesProvider) {
    this.useCasesProvider = useCasesProvider;
  }

  @Override
  public TrashViewModel get() {
    return newInstance(useCasesProvider.get());
  }

  public static TrashViewModel_Factory create(Provider<TaskUseCases> useCasesProvider) {
    return new TrashViewModel_Factory(useCasesProvider);
  }

  public static TrashViewModel newInstance(TaskUseCases useCases) {
    return new TrashViewModel(useCases);
  }
}
