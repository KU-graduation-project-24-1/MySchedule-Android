package com.uuranus.myschedule.core.network.datasource

import android.util.Log
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.myschedule.core.network.model.DeletePossibleTimeBody
import com.uuranus.myschedule.core.network.model.PatchScheduleBody
import com.uuranus.myschedule.core.network.model.PostPossibleTimeBody
import com.uuranus.myschedule.core.network.model.PostScheduleBody
import com.uuranus.myschedule.core.network.model.mapper.toData
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class ScheduleDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : ScheduleDataSource {

    override suspend fun getMonthlySchedules(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {
        val response = service.getMonthlySchedules(
            "Bearer $accessToken",
            storeId,
            dateYM
        )

        if (response.isSuccessful) {
            return response.body()?.result?.toData() ?: hashMapOf()
        } else {
            throw Exception(response.message())
        }
    }

    override suspend fun requestFillIn(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        println("request $scheduleId")
        val response = service.postScheduleCover(
            "Bearer $accessToken",
            scheduleId
        )

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.message())
        }

    }

    override suspend fun acceptFillIn(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        return true
    }

    override suspend fun getMonthlyPossibleTimes(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>> {
        val response = service.getMonthlyPossibleTimes(
            "Bearer $accessToken",
            storeId = storeId,
            dateYM = dateYM
        )

        if (response.isSuccessful) {
            return response.body()?.result?.toData() ?: hashMapOf()
        } else {
            throw Exception(response.message())
        }
    }

    override suspend fun addPossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int {
        val response = service.postPossibleTime(
            "Bearer $accessToken",
            PostPossibleTimeBody(
                storeId = storeId,
                date = dateYMD,
                startTime = startTime,
                endTime = endTime
            )
        )

        if (response.isSuccessful) {
            return response.body()?.result?.storeAvailableScheduleId ?: -1
        } else {
            throw Exception(response.message())
        }
    }

    override suspend fun deletePossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): Boolean {
println("deletE! $storeMemberAvailableTimeId")
        val response = service.deletePossibleTime(
            "Bearer $accessToken",
            DeletePossibleTimeBody(
                storeId = storeId,
                storeAvailableScheduleId = storeMemberAvailableTimeId
            )
        )
        println("delete ${response.body()}")
        if (response.isSuccessful) {
            return true
        } else {
            println("delete error ${response.errorBody()}")
            throw Exception(response.message())
        }
    }

    override suspend fun changedSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        val response = service.patchSchedule(
            "Bearer $accessToken",
            PatchScheduleBody(
                scheduleId = scheduleUpdate.scheduleId,
                employeeId = scheduleUpdate.memberId,
                startTime = scheduleUpdate.startTime,
                endTime = scheduleUpdate.endTime
            )
        )
        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body()?.code.toString())
            throw Exception(response.body()?.message ?: "")
        }
    }

    override suspend fun deleteSchedule(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
    ): Boolean {
        val response = service.deleteSchedule(
            "Bearer $accessToken",
            storeId = storeId,
            scheduleId = scheduleId
        )
        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body()?.code.toString())
            throw Exception(response.body()?.message ?: "")
        }
    }

    override suspend fun addSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        val response = service.postSchedule(
            "Bearer $accessToken",
            PostScheduleBody(
                storeId,
                scheduleUpdate.memberId,
                scheduleUpdate.dateDashString,
                scheduleUpdate.startTime,
                scheduleUpdate.endTime
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body()?.code.toString())
            throw Exception(response.body()?.message ?: "")
        }
    }
}