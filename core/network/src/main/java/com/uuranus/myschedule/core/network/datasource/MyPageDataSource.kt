package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageDataSource {

    suspend fun getFixedPossibleTimes(
        accessToken: String,
        storeId: Int,
    ): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int

    suspend fun deleteFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        storeAvailableTimeByDayId: Int,
    ): Boolean

    suspend fun getStoreSalesInformation(
        accessToken: String,
        storeId: Int,
    ): List<StoreSalesInformation>

    suspend fun addWorkerNum(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        workerNum: Int,
    ): Boolean

    suspend fun addStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int

    suspend fun deleteStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
        storeOperationInfoId: Int,
    ): Boolean

    suspend fun deleteStore(
        accessToken: String,
        storeId: Int,
    ): Boolean
}