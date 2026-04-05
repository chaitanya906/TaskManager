package com.taskflow;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class TaskFlowApp_MembersInjector implements MembersInjector<TaskFlowApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public TaskFlowApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<TaskFlowApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new TaskFlowApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(TaskFlowApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.taskflow.TaskFlowApp.workerFactory")
  public static void injectWorkerFactory(TaskFlowApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
