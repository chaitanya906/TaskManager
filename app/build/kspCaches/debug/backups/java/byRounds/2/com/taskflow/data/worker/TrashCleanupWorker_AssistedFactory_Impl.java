package com.taskflow.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class TrashCleanupWorker_AssistedFactory_Impl implements TrashCleanupWorker_AssistedFactory {
  private final TrashCleanupWorker_Factory delegateFactory;

  TrashCleanupWorker_AssistedFactory_Impl(TrashCleanupWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public TrashCleanupWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<TrashCleanupWorker_AssistedFactory> create(
      TrashCleanupWorker_Factory delegateFactory) {
    return InstanceFactory.create(new TrashCleanupWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<TrashCleanupWorker_AssistedFactory> createFactoryProvider(
      TrashCleanupWorker_Factory delegateFactory) {
    return InstanceFactory.create(new TrashCleanupWorker_AssistedFactory_Impl(delegateFactory));
  }
}
