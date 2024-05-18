package com.uuranus.myschedule.feat.bossworkermanage;

import com.uuranus.domain.DeleteWorker;
import com.uuranus.domain.EditWorkerType;
import com.uuranus.domain.GetAllWorkersInfo;
import com.uuranus.domain.GetUserDataUseCase;
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
    "KotlinInternalInJava"
})
public final class BossWorkerManageViewModel_Factory implements Factory<BossWorkerManageViewModel> {
  private final Provider<GetUserDataUseCase> getUserDataUseCaseProvider;

  private final Provider<GetAllWorkersInfo> getAllWorkersInfoProvider;

  private final Provider<DeleteWorker> deleteWorkerProvider;

  private final Provider<EditWorkerType> editWorkerTypeProvider;

  public BossWorkerManageViewModel_Factory(Provider<GetUserDataUseCase> getUserDataUseCaseProvider,
      Provider<GetAllWorkersInfo> getAllWorkersInfoProvider,
      Provider<DeleteWorker> deleteWorkerProvider,
      Provider<EditWorkerType> editWorkerTypeProvider) {
    this.getUserDataUseCaseProvider = getUserDataUseCaseProvider;
    this.getAllWorkersInfoProvider = getAllWorkersInfoProvider;
    this.deleteWorkerProvider = deleteWorkerProvider;
    this.editWorkerTypeProvider = editWorkerTypeProvider;
  }

  @Override
  public BossWorkerManageViewModel get() {
    return newInstance(getUserDataUseCaseProvider.get(), getAllWorkersInfoProvider.get(), deleteWorkerProvider.get(), editWorkerTypeProvider.get());
  }

  public static BossWorkerManageViewModel_Factory create(
      Provider<GetUserDataUseCase> getUserDataUseCaseProvider,
      Provider<GetAllWorkersInfo> getAllWorkersInfoProvider,
      Provider<DeleteWorker> deleteWorkerProvider,
      Provider<EditWorkerType> editWorkerTypeProvider) {
    return new BossWorkerManageViewModel_Factory(getUserDataUseCaseProvider, getAllWorkersInfoProvider, deleteWorkerProvider, editWorkerTypeProvider);
  }

  public static BossWorkerManageViewModel newInstance(GetUserDataUseCase getUserDataUseCase,
      GetAllWorkersInfo getAllWorkersInfo, DeleteWorker deleteWorker,
      EditWorkerType editWorkerType) {
    return new BossWorkerManageViewModel(getUserDataUseCase, getAllWorkersInfo, deleteWorker, editWorkerType);
  }
}
