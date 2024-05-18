package com.uuranus.data.repository.home

import android.util.Log
import com.uuranus.datastore.UserDataStore
import com.uuranus.datastore.UserPreferences
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.datasource.ScheduleDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore,
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

    override suspend fun getAllWorkers(storeId: Int): List<WorkerInfo> {
        return dataSource.getAllWorkers(storeId)
    }

    override suspend fun changeSchedule(
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        return dataSource.changedSchedule(
            storeId, scheduleUpdate
        )
    }

    override suspend fun deleteSchedule(scheduleId: Int): Boolean {
        return dataSource.deleteSchedule(scheduleId)
    }

    override suspend fun addSchedule(storeId: Int, scheduleUpdate: ScheduleUpdate): Boolean {
        return dataSource.addSchedule(storeId, scheduleUpdate)
    }


}