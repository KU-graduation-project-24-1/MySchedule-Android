package com.uuranus.data.di

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.home.HomeRepositoryImpl
import com.uuranus.myschedule.core.network.datasource.CalendarDataSource
import com.uuranus.myschedule.core.network.datasource.CalendarDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindCalendarDataSource(
        dataSource: CalendarDataSourceImpl,
    ): CalendarDataSource

    @Binds
    fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

}
