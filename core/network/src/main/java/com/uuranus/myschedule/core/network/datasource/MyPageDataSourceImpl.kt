package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.network.model.DeleteFixedScheduleBody
import com.uuranus.myschedule.core.network.model.PathFixedScheduleBody
import com.uuranus.myschedule.core.network.model.mapper.toData
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : MyPageDataSource {
    override suspend fun getFixedPossibleTimes(
        accessToken: String,
        storeId: Int,
    ): List<List<TimeRange>> {
        println("storeid $storeId")
        val response = service.getFixedSchedule(
            accessToken,
            storeId
        )

        println("response ${response.body()}")
        if (response.isSuccessful) {
            return listOf(
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
            )
        } else {
            throw Exception(response.body()?.message)
        }

    }

    override suspend fun addFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Boolean {
        val response = service.patchFixedSchedule(
            accessToken,
            storeId,
            PathFixedScheduleBody(
                storeId = storeId,
                dayOfWeek = weekNum,
                startTime = startTime,
                endTime = endTime
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.body()?.message)
        }
    }

    override suspend fun deleteFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        storeAvailableTimeByDayId: Int,
    ): Boolean {
        val response = service.deleteFixedSchedule(
            accessToken,
            storeId,
            DeleteFixedScheduleBody(
                storeId,
                storeAvailableTimeByDayId
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.body()?.message)
        }
    }

    override suspend fun getStoreSalesInformation(accessToken: String): List<StoreSalesInformation> {
        return listOf(
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
        )
    }

    override suspend fun addWorkerNum(accessToken: String, weekNum: Int, workerNum: Int): Boolean {
        return true
    }

    override suspend fun addStoreOpeningHourTime(
        accessToken: String,
        startTime: String,
        endTime: String,
    ): Int {
        return -1
    }

    override suspend fun deleteStoreOpeningHourTime(
        accessToken: String,
        weekNum: Int,
        requiredEmployees: Int,
        startTime: String,
        endTime: String,
    ): Boolean {
        return true
    }

    override suspend fun deleteStore(accessToken: String, storeId: Int): Boolean {
        val response = service.deleteStore(
            "Bearer $accessToken",
            storeId
        )

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.message())
        }
    }
}