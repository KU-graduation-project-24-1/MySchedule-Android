package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate

interface ScheduleDataSource {
    suspend fun getMonthlySchedules(
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>>

    suspend fun requestFillIn(
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean

    suspend fun acceptFillIn(
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean

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

    suspend fun changedSchedule(
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean

    suspend fun deleteSchedule(
        scheduleId: Int,
    ): Boolean

    suspend fun addSchedule(
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean
}