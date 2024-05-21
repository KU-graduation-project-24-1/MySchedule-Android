package com.uuranus.data.repository.home

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.myschedule.core.network.datasource.ScheduleDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: ScheduleDataSource,
) : HomeRepository {
    override suspend fun getMonthlySchedules(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {
        return dataSource.getMonthlySchedules(accessToken, storeId, dateYM)
    }

    override suspend fun requestFillIn(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        return dataSource.requestFillIn(accessToken, storeId, scheduleId, memberId)
    }

    override suspend fun acceptFillIn(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        return dataSource.acceptFillIn(accessToken, storeId, scheduleId, memberId)
    }

    override suspend fun getMonthlyPossibleTimes(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>> {
        return dataSource.getMonthlyPossibleTimes(accessToken, storeId, dateYM)
    }

    override suspend fun addPossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int {
        return dataSource.addPossibleTime(
            accessToken,
            memberId,
            storeId,
            dateYMD,
            startTime,
            endTime
        )
    }

    override suspend fun deletePossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String {
        return dataSource.deletePossibleTime(
            accessToken,
            memberId,
            storeId,
            storeMemberAvailableTimeId
        )
    }

    override suspend fun changeSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        return dataSource.changedSchedule(
            accessToken,
            storeId, scheduleUpdate
        )
    }

    override suspend fun deleteSchedule(
        accessToken: String,
        storeId: Int, scheduleId: Int,
    ): Boolean {
        return dataSource.deleteSchedule(accessToken, storeId, scheduleId)
    }

    override suspend fun addSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        return dataSource.addSchedule(accessToken, storeId, scheduleUpdate)
    }


}