package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo

interface ScheduleDataSource {

    suspend fun getMonthlySchedules(storeId: Int, dateYM: String): HashMap<String, List<MyScheduleInfo>>

    suspend fun requestFillIn(scheduleId: Int)

    suspend fun acceptFillIn(scheduleId: Int)

    suspend fun getMonthlyPossibleTimes(
        storeId: Int, dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>>

    suspend fun addPossibleTime(
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int

    suspend fun deletePossibleTime(
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String
}