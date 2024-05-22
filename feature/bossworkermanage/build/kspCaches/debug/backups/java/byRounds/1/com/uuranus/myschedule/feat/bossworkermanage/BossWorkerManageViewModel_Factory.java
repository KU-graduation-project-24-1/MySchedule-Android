package com.uuranus.myschedule.feat.bossworkermanage;

import com.uuranus.domain.DeleteWorkerUseCase;
import com.uuranus.domain.EditWorkerTypeUseCase;
import com.uuranus.domain.GetAllWorkersInfoUseCase;
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

  private final Provider<GetAllWorkersInfoUseCase> getAllWorkersInfoProvider;

  private final Provider<DeleteWorkerUseCase> deleteWorkerProvider;

  private final Provider<EditWorkerTypeUseCase> editWorkerTypeProvider;

  public BossWorkerManageViewModel_Factory(Provider<GetUserDataUseCase> getUserDataUseCaseProvider,
      Provider<GetAllWorkersInfoUseCase> getAllWorkersInfoProvider,
      Provider<DeleteWorkerUseCase> deleteWorkerProvider,
      Provider<EditWorkerTypeUseCase> editWorkerTypeProvider) {
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
      Provider<GetAllWorkersInfoUseCase> getAllWorkersInfoProvider,
      Provider<DeleteWorkerUseCase> deleteWorkerProvider,
      Provider<EditWorkerTypeUseCase> editWorkerTypeProvider) {
    return new BossWorkerManageViewModel_Factory(getUserDataUseCaseProvider, getAllWorkersInfoProvider, deleteWorkerProvider, editWorkerTypeProvider);
  }

  public static BossWorkerManageViewModel newInstance(GetUserDataUseCase getUserDataUseCase,
      GetAllWorkersInfoUseCase getAllWorkersInfo, DeleteWorkerUseCase deleteWorker,
      EditWorkerTypeUseCase editWorkerType) {
    return new BossWorkerManageViewModel(getUserDataUseCase, getAllWorkersInfo, deleteWorker, editWorkerType);
  }
}
