package com.uuranus.myschedule.core.network.datasource

import android.util.Log
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.model.PatchScheduleBody
import com.uuranus.myschedule.core.network.model.PostScheduleBody
import com.uuranus.myschedule.core.network.model.mapper.toData
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

private val possibleSchedules = hashMapOf(
    "2024-05-13" to listOf(
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 1,
            startTime = "15:00",
            endTime = "21:00"
        )
    ),
    "2024-05-16" to listOf(
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 6,
            startTime = "13:00",
            endTime = "17:00"
        )
    ),
    "2024-05-23" to listOf(
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 2,
            startTime = "13:00",
            endTime = "17:00"
        ),
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 4,
            startTime = "18:00",
            endTime = "20:00"
        )
    ),
    "2024-05-27" to listOf(
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 7,
            startTime = "13:00",
            endTime = "17:00"
        )
    ),
    "2024-06-30" to listOf(
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 10,
            startTime = "13:00",
            endTime = "17:00"
        ),
        MyPossibleTimeInfo(
            storeMemberAvailableTimeId = 11,
            startTime = "18:00",
            endTime = "20:00"
        )
    )
)


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
        return true
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
        return possibleSchedules
    }

    override suspend fun addPossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int {
        return 9 //storeMemberAvailableTimeId
    }

    override suspend fun deletePossibleTime(
        accessToken: String,
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String {
        return "근무 가능한 시간 정보를 삭제하였습니다."
    }

    override suspend fun changedSchedule(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        println("change $scheduleUpdate")
        val response = service.patchSchedule(
            "Bearer $accessToken",
            PatchScheduleBody(
                scheduleId = scheduleUpdate.scheduleId,
                employeeId = scheduleUpdate.memberId,
                startTime = scheduleUpdate.startTime,
                endTime = scheduleUpdate.endTime
            )
        )
        println("Response ${response.body()}")
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
        println("addschedule ${response.body()}")
        if (response.isSuccessful) {
            return true
        } else {
            Log.e("마이스케줄", response.body()?.code.toString())
            throw Exception(response.body()?.message ?: "")
        }
    }
}