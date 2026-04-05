package com.taskflow.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.taskflow.domain.usecase.CleanupOldDeletedTasksUseCase;
import dagger.internal.DaggerGenerated;
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
public final class TrashCleanupWorker_Factory {
  private final Provider<CleanupOldDeletedTasksUseCase> cleanupProvider;

  public TrashCleanupWorker_Factory(Provider<CleanupOldDeletedTasksUseCase> cleanupProvider) {
    this.cleanupProvider = cleanupProvider;
  }

  public TrashCleanupWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, cleanupProvider.get());
  }

  public static TrashCleanupWorker_Factory create(
      Provider<CleanupOldDeletedTasksUseCase> cleanupProvider) {
    return new TrashCleanupWorker_Factory(cleanupProvider);
  }

  public static TrashCleanupWorker newInstance(Context context, WorkerParameters params,
      CleanupOldDeletedTasksUseCase cleanup) {
    return new TrashCleanupWorker(context, params, cleanup);
  }
}
