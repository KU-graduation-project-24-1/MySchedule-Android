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
    ): Int {
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
        storeId: Int,
        storeAvailableTimeByDayId: Int,
    ): Boolean {
        return dataSource.deleteFixedPossibleTime(
            accessToken,
            storeId,
            storeAvailableTimeByDayId
        )
    }

    override suspend fun getStoreSalesInformation(
        accessToken: String,
        storeId: Int,
    ): List<StoreSalesInformation> {
        return dataSource.getStoreSalesInformation(accessToken, storeId)
    }

    override suspend fun addWorkerNum(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        workerNum: Int,
    ): Boolean {
        return dataSource.addWorkerNum(accessToken, storeId, weekNum, workerNum)
    }

    override suspend fun addStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int {
        return dataSource.addStoreOpeningHourTime(
            accessToken,
            storeId,
            weekNum,
            startTime,
            endTime
        )
    }

    override suspend fun deleteStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
        storeOperationInfoId: Int,
    ): Boolean {
        return dataSource.deleteStoreOpeningHourTime(
            accessToken,
            storeId,
            storeOperationInfoId
        )
    }

    override suspend fun deleteStore(accessToken: String, storeId: Int): Boolean {
        return dataSource.deleteStore(accessToken, storeId)
    }
}