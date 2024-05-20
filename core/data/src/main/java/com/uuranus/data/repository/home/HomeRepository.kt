package com.uuranus.data.repository.home

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo

interface HomeRepository {
    suspend fun getMonthlySchedules(
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>>

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

    suspend fun changeSchedule(
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