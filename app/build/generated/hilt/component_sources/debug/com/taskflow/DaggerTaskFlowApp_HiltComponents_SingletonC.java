package com.taskflow;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.taskflow.data.local.TaskFlowDatabase;
import com.taskflow.data.local.dao.TaskDao;
import com.taskflow.data.repository.TaskRepositoryImpl;
import com.taskflow.data.worker.TrashCleanupWorker;
import com.taskflow.data.worker.TrashCleanupWorker_AssistedFactory;
import com.taskflow.di.DatabaseModule_ProvideDaoFactory;
import com.taskflow.di.DatabaseModule_ProvideDbFactory;
import com.taskflow.di.UseCaseModule_ProvideTaskUseCasesFactory;
import com.taskflow.domain.repository.TaskRepository;
import com.taskflow.domain.usecase.CleanupOldDeletedTasksUseCase;
import com.taskflow.domain.usecase.CreateTaskUseCase;
import com.taskflow.domain.usecase.GetActiveTasksUseCase;
import com.taskflow.domain.usecase.GetDeletedTasksUseCase;
import com.taskflow.domain.usecase.GetTaskByIdUseCase;
import com.taskflow.domain.usecase.PermanentlyDeleteTaskUseCase;
import com.taskflow.domain.usecase.RestoreTaskUseCase;
import com.taskflow.domain.usecase.SoftDeleteTaskUseCase;
import com.taskflow.domain.usecase.TaskUseCases;
import com.taskflow.domain.usecase.UpdateTaskUseCase;
import com.taskflow.presentation.MainActivity;
import com.taskflow.presentation.viewmodel.TaskDetailViewModel;
import com.taskflow.presentation.viewmodel.TaskDetailViewModel_HiltModules;
import com.taskflow.presentation.viewmodel.TaskListViewModel;
import com.taskflow.presentation.viewmodel.TaskListViewModel_HiltModules;
import com.taskflow.presentation.viewmodel.TrashViewModel;
import com.taskflow.presentation.viewmodel.TrashViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerTaskFlowApp_HiltComponents_SingletonC {
  private DaggerTaskFlowApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public TaskFlowApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements TaskFlowApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements TaskFlowApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements TaskFlowApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements TaskFlowApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements TaskFlowApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements TaskFlowApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements TaskFlowApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public TaskFlowApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends TaskFlowApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends TaskFlowApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends TaskFlowApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends TaskFlowApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(3).put(LazyClassKeyProvider.com_taskflow_presentation_viewmodel_TaskDetailViewModel, TaskDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_taskflow_presentation_viewmodel_TaskListViewModel, TaskListViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_taskflow_presentation_viewmodel_TrashViewModel, TrashViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_taskflow_presentation_viewmodel_TaskDetailViewModel = "com.taskflow.presentation.viewmodel.TaskDetailViewModel";

      static String com_taskflow_presentation_viewmodel_TaskListViewModel = "com.taskflow.presentation.viewmodel.TaskListViewModel";

      static String com_taskflow_presentation_viewmodel_TrashViewModel = "com.taskflow.presentation.viewmodel.TrashViewModel";

      @KeepFieldType
      TaskDetailViewModel com_taskflow_presentation_viewmodel_TaskDetailViewModel2;

      @KeepFieldType
      TaskListViewModel com_taskflow_presentation_viewmodel_TaskListViewModel2;

      @KeepFieldType
      TrashViewModel com_taskflow_presentation_viewmodel_TrashViewModel2;
    }
  }

  private static final class ViewModelCImpl extends TaskFlowApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<TaskDetailViewModel> taskDetailViewModelProvider;

    private Provider<TaskListViewModel> taskListViewModelProvider;

    private Provider<TrashViewModel> trashViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.taskDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.taskListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.trashViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(3).put(LazyClassKeyProvider.com_taskflow_presentation_viewmodel_TaskDetailViewModel, ((Provider) taskDetailViewModelProvider)).put(LazyClassKeyProvider.com_taskflow_presentation_viewmodel_TaskListViewModel, ((Provider) taskListViewModelProvider)).put(LazyClassKeyProvider.com_taskflow_presentation_viewmodel_TrashViewModel, ((Provider) trashViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_taskflow_presentation_viewmodel_TaskDetailViewModel = "com.taskflow.presentation.viewmodel.TaskDetailViewModel";

      static String com_taskflow_presentation_viewmodel_TaskListViewModel = "com.taskflow.presentation.viewmodel.TaskListViewModel";

      static String com_taskflow_presentation_viewmodel_TrashViewModel = "com.taskflow.presentation.viewmodel.TrashViewModel";

      @KeepFieldType
      TaskDetailViewModel com_taskflow_presentation_viewmodel_TaskDetailViewModel2;

      @KeepFieldType
      TaskListViewModel com_taskflow_presentation_viewmodel_TaskListViewModel2;

      @KeepFieldType
      TrashViewModel com_taskflow_presentation_viewmodel_TrashViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.taskflow.presentation.viewmodel.TaskDetailViewModel 
          return (T) new TaskDetailViewModel(singletonCImpl.provideTaskUseCasesProvider.get(), viewModelCImpl.savedStateHandle);

          case 1: // com.taskflow.presentation.viewmodel.TaskListViewModel 
          return (T) new TaskListViewModel(singletonCImpl.provideTaskUseCasesProvider.get());

          case 2: // com.taskflow.presentation.viewmodel.TrashViewModel 
          return (T) new TrashViewModel(singletonCImpl.provideTaskUseCasesProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends TaskFlowApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends TaskFlowApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends TaskFlowApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<TaskFlowDatabase> provideDbProvider;

    private Provider<TaskDao> provideDaoProvider;

    private Provider<TaskRepositoryImpl> taskRepositoryImplProvider;

    private Provider<TaskRepository> bindTaskRepositoryProvider;

    private Provider<TrashCleanupWorker_AssistedFactory> trashCleanupWorker_AssistedFactoryProvider;

    private Provider<TaskUseCases> provideTaskUseCasesProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private CleanupOldDeletedTasksUseCase cleanupOldDeletedTasksUseCase() {
      return new CleanupOldDeletedTasksUseCase(bindTaskRepositoryProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return Collections.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>singletonMap("com.taskflow.data.worker.TrashCleanupWorker", ((Provider) trashCleanupWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private GetActiveTasksUseCase getActiveTasksUseCase() {
      return new GetActiveTasksUseCase(bindTaskRepositoryProvider.get());
    }

    private GetDeletedTasksUseCase getDeletedTasksUseCase() {
      return new GetDeletedTasksUseCase(bindTaskRepositoryProvider.get());
    }

    private GetTaskByIdUseCase getTaskByIdUseCase() {
      return new GetTaskByIdUseCase(bindTaskRepositoryProvider.get());
    }

    private CreateTaskUseCase createTaskUseCase() {
      return new CreateTaskUseCase(bindTaskRepositoryProvider.get());
    }

    private UpdateTaskUseCase updateTaskUseCase() {
      return new UpdateTaskUseCase(bindTaskRepositoryProvider.get());
    }

    private SoftDeleteTaskUseCase softDeleteTaskUseCase() {
      return new SoftDeleteTaskUseCase(bindTaskRepositoryProvider.get());
    }

    private RestoreTaskUseCase restoreTaskUseCase() {
      return new RestoreTaskUseCase(bindTaskRepositoryProvider.get());
    }

    private PermanentlyDeleteTaskUseCase permanentlyDeleteTaskUseCase() {
      return new PermanentlyDeleteTaskUseCase(bindTaskRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDbProvider = DoubleCheck.provider(new SwitchingProvider<TaskFlowDatabase>(singletonCImpl, 3));
      this.provideDaoProvider = DoubleCheck.provider(new SwitchingProvider<TaskDao>(singletonCImpl, 2));
      this.taskRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 1);
      this.bindTaskRepositoryProvider = DoubleCheck.provider((Provider) taskRepositoryImplProvider);
      this.trashCleanupWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<TrashCleanupWorker_AssistedFactory>(singletonCImpl, 0));
      this.provideTaskUseCasesProvider = DoubleCheck.provider(new SwitchingProvider<TaskUseCases>(singletonCImpl, 4));
    }

    @Override
    public void injectTaskFlowApp(TaskFlowApp taskFlowApp) {
      injectTaskFlowApp2(taskFlowApp);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private TaskFlowApp injectTaskFlowApp2(TaskFlowApp instance) {
      TaskFlowApp_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.taskflow.data.worker.TrashCleanupWorker_AssistedFactory 
          return (T) new TrashCleanupWorker_AssistedFactory() {
            @Override
            public TrashCleanupWorker create(Context context, WorkerParameters params) {
              return new TrashCleanupWorker(context, params, singletonCImpl.cleanupOldDeletedTasksUseCase());
            }
          };

          case 1: // com.taskflow.data.repository.TaskRepositoryImpl 
          return (T) new TaskRepositoryImpl(singletonCImpl.provideDaoProvider.get());

          case 2: // com.taskflow.data.local.dao.TaskDao 
          return (T) DatabaseModule_ProvideDaoFactory.provideDao(singletonCImpl.provideDbProvider.get());

          case 3: // com.taskflow.data.local.TaskFlowDatabase 
          return (T) DatabaseModule_ProvideDbFactory.provideDb(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.taskflow.domain.usecase.TaskUseCases 
          return (T) UseCaseModule_ProvideTaskUseCasesFactory.provideTaskUseCases(singletonCImpl.getActiveTasksUseCase(), singletonCImpl.getDeletedTasksUseCase(), singletonCImpl.getTaskByIdUseCase(), singletonCImpl.createTaskUseCase(), singletonCImpl.updateTaskUseCase(), singletonCImpl.softDeleteTaskUseCase(), singletonCImpl.restoreTaskUseCase(), singletonCImpl.permanentlyDeleteTaskUseCase(), singletonCImpl.cleanupOldDeletedTasksUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
