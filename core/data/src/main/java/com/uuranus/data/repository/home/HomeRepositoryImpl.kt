package com.uuranus.data.repository.home

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.network.datasource.ScheduleDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: ScheduleDataSource,
) : HomeRepository {
    override suspend fun getMonthlySchedules(
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {
        return dataSource.getMonthlySchedules(storeId, dateYM)
    }

    override suspend fun requestFillIn(scheduleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun acceptFillIn(scheduleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getMonthlyPossibleTimes(
        storeId: Int, dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>> {
        return dataSource.getMonthlyPossibleTimes(storeId, dateYM)
    }

    override suspend fun addPossibleTime(
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int {
        return dataSource.addPossibleTime(memberId, storeId, dateYMD, startTime, endTime)
    }

    override suspend fun deletePossibleTime(
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String {
        return dataSource.deletePossibleTime(memberId, storeId, storeMemberAvailableTimeId)
    }
}