package com.uuranus.data.repository.mypage

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageRepository {

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
    ): Boolean

    suspend fun deleteFixedPossibleTime(
        accessToken: String,
        weekNum: Int,
        storeAvailableTimeByDayId: Int,
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
        weekNum: Int,
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