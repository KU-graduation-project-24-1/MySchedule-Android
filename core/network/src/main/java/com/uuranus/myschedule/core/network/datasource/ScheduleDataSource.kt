package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate

interface ScheduleDataSource {
    suspend fun getMonthlySchedules(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>>

    suspend fun requestFillIn(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean

    suspend fun acceptFillIn(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean

    suspend fun getMonthlyPossibleTimes(
        accessToken: String,
        storeId: Int, dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>>

    suspend fun addPossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int

    suspend fun deletePossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String

    suspend fun changedSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean

    suspend fun deleteSchedule(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
    ): Boolean

    suspend fun addSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean
}