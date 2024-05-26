package com.uuranus.myschedule.core.network.datasource

import android.util.Log
import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.network.model.DeleteFixedScheduleBody
import com.uuranus.myschedule.core.network.model.DeleteOperationInfoBody
import com.uuranus.myschedule.core.network.model.PatchWorkerNumBody
import com.uuranus.myschedule.core.network.model.PathFixedScheduleBody
import com.uuranus.myschedule.core.network.model.PostOperationInfoBody
import com.uuranus.myschedule.core.network.model.mapper.toData
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject


private val weekDays =
    listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")

class MyPageDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : MyPageDataSource {
    override suspend fun getFixedPossibleTimes(
        accessToken: String,
        storeId: Int,
    ): List<List<TimeRange>> {
        val response = service.getFixedSchedule(
            "Bearer $accessToken",
            storeId
        )

        if (response.isSuccessful) {
            return response.body()?.result?.toData() ?: listOf(
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
            )
        } else {
            Log.e("마이스케줄", response.body().toString())
            throw Exception(response.body()?.message)
        }

    }

    override suspend fun addFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int {
        val response = service.patchFixedSchedule(
            "Bearer $accessToken",
            storeId,
            PathFixedScheduleBody(
                storeId = storeId,
                dayOfWeek = weekDays[weekNum],
                startTime = startTime,
                endTime = endTime
            )
        )

        if (response.isSuccessful) {
            return response.body()?.result?.storeAvailableTimeByDayId ?: -1
        } else {
            Log.e("마이스케줄", response.body().toString())
            throw Exception(response.body()?.message)
        }
    }

    override suspend fun deleteFixedPossibleTime(
        accessToken: String,
        storeId: Int,
        storeAvailableTimeByDayId: Int,
    ): Boolean {
        val response = service.deleteFixedSchedule(
            "Bearer $accessToken",
            storeId,
            DeleteFixedScheduleBody(
                storeId,
                storeAvailableTimeByDayId
            )
        )

        println("response ${response.body()}")
        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body().toString())
            throw Exception(response.body()?.message)
        }
    }

    override suspend fun getStoreSalesInformation(
        accessToken: String,
        storeId: Int,
    ): List<StoreSalesInformation> {
        val list = mutableListOf<StoreSalesInformation>()

        for (weekDay in weekDays) {
            val response = service.getOperationInfo(
                "Bearer $accessToken",
                storeId,
                weekDay
            )

            if (response.isSuccessful) {
                list.add(
                    response.body()?.result?.toData() ?: StoreSalesInformation(
                        0,
                        emptyList()
                    )
                )
            } else {
                Log.e("마이스케줄", response.body().toString())
                throw Exception(response.body()?.message)
            }
        }
        return list
    }

    override suspend fun addWorkerNum(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        workerNum: Int,
    ): Boolean {
        val response = service.patchWorkerNum(
            "Bearer $accessToken",
            PatchWorkerNumBody(
                storeId,
                weekDays[weekNum],
                workerNum
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body().toString())
            throw Exception(response.body()?.message)
        }

    }

    override suspend fun addStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int {

        val response = service.postOperationInfo(
            "Bearer $accessToken",
            storeId,
            PostOperationInfoBody(
                storeId,
                weekDays[weekNum],
                startTime,
                endTime
            )
        )

        if (response.isSuccessful) {
            return response.body()?.result?.storeOperationInfoId ?: -1
        } else {
            Log.e("마이스케줄", response.errorBody()?.string() ?: "")
            throw Exception(response.body().toString())
        }
    }

    override suspend fun deleteStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
        storeOperationInfoId: Int,
    ): Boolean {

        val response = service.deleteOperationInfo(
            "Bearer $accessToken",
            storeId,
            DeleteOperationInfoBody(
                storeOperationInfoId
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body().toString())
            throw Exception(response.body()?.message)
        }
    }

    override suspend fun deleteStore(accessToken: String, storeId: Int): Boolean {
        val response = service.deleteStore(
            "Bearer $accessToken",
            storeId
        )

        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body().toString())
            throw Exception(response.message())
        }
    }
}