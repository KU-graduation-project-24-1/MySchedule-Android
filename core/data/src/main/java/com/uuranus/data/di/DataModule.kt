package com.uuranus.data.di

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.home.HomeRepositoryImpl
import com.uuranus.data.repository.login.LoginRepository
import com.uuranus.data.repository.login.LoginRepositoryImpl
import com.uuranus.data.repository.mypage.MyPageRepository
import com.uuranus.data.repository.mypage.MyPageRepositoryImpl
import com.uuranus.data.repository.user.UserRepository
import com.uuranus.data.repository.user.UserRepositoryImpl
import com.uuranus.data.repository.workermanage.WorkerManageRepository
import com.uuranus.data.repository.workermanage.WorkerManageRepositoryImpl
import com.uuranus.myschedule.core.network.datasource.LoginDataSource
import com.uuranus.myschedule.core.network.datasource.LoginDataSourceImpl
import com.uuranus.myschedule.core.network.datasource.MyPageDataSource
import com.uuranus.myschedule.core.network.datasource.MyPageDataSourceImpl
import com.uuranus.myschedule.core.network.datasource.ScheduleDataSource
import com.uuranus.myschedule.core.network.datasource.ScheduleDataSourceImpl
import com.uuranus.myschedule.core.network.datasource.WorkerDataSource
import com.uuranus.myschedule.core.network.datasource.WorkerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindLoginDataSource(
        loginDataSourceImpl: LoginDataSourceImpl
    ): LoginDataSource

    @Binds
    fun bindScheduleDataSource(
        dataSource: ScheduleDataSourceImpl,
    ): ScheduleDataSource

    @Binds
    fun bindWorkerDataSource(
        dataSource: WorkerDataSourceImpl,
    ): WorkerDataSource

    @Binds
    fun bindMyPageDataSource(
        dataSource: MyPageDataSourceImpl,
    ): MyPageDataSource

    @Binds
    fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindsWorkerRepository(workerRepositoryImpl: WorkerManageRepositoryImpl): WorkerManageRepository

    @Binds
    fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindsMyPageRepository(mypageRepositoryImpl: MyPageRepositoryImpl): MyPageRepository

}
