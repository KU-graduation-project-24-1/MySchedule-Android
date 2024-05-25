package com.uuranus.data.repository.mypage

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.network.datasource.MyPageDataSource
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val dataSource: MyPageDataSource,
) : MyPageRepository {
    override suspend fun getFixedPossibleTimes(
        accessToken: String,
        storeId: Int,
    ): List<List<TimeRange>> {
        return dataSource.getFixedPossibleTimes(accessToken, storeId)
    }

    override suspend fun addFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Boolean {
        return dataSource.addFixedPossibleTime(
            accessToken,
            storeId,
            weekNum,
            startTime,
            endTime,
        )
    }

    override suspend fun deleteFixedPossibleTime(
        accessToken: String,
        weekNum: Int,
        storeAvailableTimeByDayId: Int,
    ): Boolean {
        return dataSource.deleteFixedPossibleTime(
            accessToken,
            weekNum,
            storeAvailableTimeByDayId
        )
    }

    override suspend fun getStoreSalesInformation(
        accessToken: String,
    ): List<StoreSalesInformation> {
        return dataSource.getStoreSalesInformation(accessToken)
    }

    override suspend fun addWorkerNum(accessToken: String, weekNum: Int, workerNum: Int): Boolean {
        return dataSource.addWorkerNum(accessToken, weekNum, workerNum)
    }

    override suspend fun addStoreOpeningHourTime(
        accessToken: String,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int {
        return dataSource.addStoreOpeningHourTime(
            accessToken,
            startTime,
            endTime
        )
    }

    override suspend fun deleteStoreOpeningHourTime(
        accessToken: String,
        weekNum: Int,
        requiredEmployees: Int,
        startTime: String,
        endTime: String,
    ): Boolean {
        return dataSource.deleteStoreOpeningHourTime(
            accessToken,
            weekNum,
            requiredEmployees,
            startTime,
            endTime
        )
    }

    override suspend fun deleteStore(accessToken: String, storeId: Int): Boolean {
        return dataSource.deleteStore(accessToken, storeId)
    }
}