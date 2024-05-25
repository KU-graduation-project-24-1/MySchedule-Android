package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageDataSource {

    suspend fun getFixedPossibleTimes(
        accessToken: String,
    ): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(
        accessToken: String,
    ): String

    suspend fun deleteFixedPossibleTime(
        accessToken: String,
        weekNum: Int,
        requiredEmployees: Int,
        startTime: String,
        endTime: String,
    ): Boolean

    suspend fun getStoreSalesInformation(
        accessToken: String,
    ): List<StoreSalesInformation>

    suspend fun addWorkerNum(
        accessToken: String,
        weekNum: Int,
        workerNum: Int,
    ): Boolean

    suspend fun addStoreOpeningHourTime(
        accessToken: String,
        startTime: String,
        endTime: String,
    ): Int

    suspend fun deleteStoreOpeningHourTime(
        accessToken: String,
        weekNum: Int,
        requiredEmployees: Int,
        startTime: String,
        endTime: String,
    ): Boolean

    suspend fun deleteStore(
        accessToken: String,
        storeId: Int,
    ): Boolean
}