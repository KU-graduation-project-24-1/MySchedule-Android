package com.uuranus.data.repository.mypage

import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.network.datasource.MyPageDataSource
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val dataSource: MyPageDataSource,
) : MyPageRepository {
    override suspend fun getFixedPossibleTimes(): List<List<TimeRange>> {
        return dataSource.getFixedPossibleTimes()
    }

    override suspend fun addFixedPossibleTime(): String {
        return dataSource.addFixedPossibleTime()
    }
}