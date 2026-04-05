package com.taskflow.di;

import com.taskflow.data.local.TaskFlowDatabase;
import com.taskflow.data.local.dao.TaskDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideDaoFactory implements Factory<TaskDao> {
  private final Provider<TaskFlowDatabase> dbProvider;

  public DatabaseModule_ProvideDaoFactory(Provider<TaskFlowDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TaskDao get() {
    return provideDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDaoFactory create(Provider<TaskFlowDatabase> dbProvider) {
    return new DatabaseModule_ProvideDaoFactory(dbProvider);
  }

  public static TaskDao provideDao(TaskFlowDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDao(db));
  }
}
