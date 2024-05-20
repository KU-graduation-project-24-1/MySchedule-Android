package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.model.mapper.toData
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

private var schedules = hashMapOf(
    "2024-05-16" to listOf(
        MyScheduleInfo(
            scheduleId = 9,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 2,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        )
    ),
    "2024-05-20" to listOf(
        MyScheduleInfo(
            scheduleId = 8,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 2,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        )
    ),
    "2024-05-23" to listOf(
        MyScheduleInfo(
            scheduleId = 7,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 2,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        )
    ),
    "2024-05-27" to listOf(
        MyScheduleInfo(
            scheduleId = 6,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 2,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        )
    ),
    "2024-06-30" to listOf(
        MyScheduleInfo(
            scheduleId = 5,
            startTime = "08:00",
            endTime = "13:00",
            memberId = 1,
            workerName = "이직원",
            workerType = "매니저",
            isMine = false,
            isFillInNeeded = false
        ),
        MyScheduleInfo(
            scheduleId = 3,
            startTime = "12:00",
            endTime = "15:00",
            memberId = 2,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        ),
        MyScheduleInfo(
            scheduleId = 4,
            startTime = "15:00",
            endTime = "23:59",
            memberId = 1,
            workerName = "이직원",
            workerType = "매니저",
            isMine = false,
            isFillInNeeded = true
        ),
        MyScheduleInfo(
            scheduleId = 2,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 2,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        )
    )
)
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
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {
//        val response = service.getMonthlySchedules(
//            "Bearer eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJhNDkwMDNmZWM1MTY3ZjY5OTJjMWY4NDU3MTA0NjJiOSIsInN1YiI6IjM0OTAzMTAwOTAiLCJhdXRoX3RpbWUiOjE3MTYxOTM4MzksImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwiZXhwIjoxNzE2MjM3MDM5LCJpYXQiOjE3MTYxOTM4MzksInBpY3R1cmUiOiJodHRwOi8vay5rYWthb2Nkbi5uZXQvZG4vYmY5OTdVL2J0clpOS0oyOUg0L2xKYXpabjBDcWo2TFRGWWlZYWkzSzAvaW1nXzExMHgxMTAuanBnIiwiZW1haWwiOiJ5dXIwOTIwQG5hdmVyLmNvbSJ9.ba_cpd7joYrn5Vfic_VZc7VHOJflC4OV_WhwuHVNTDZXYfCODAMzYYtU6-DH5nINSd9B_GcAqYsk8TzNMvwCz-QJzpdpfCIbUvj4-qYKtsszNOp0KhJuxsOHEK0P-fsI-Yf_pKLzBzodXuUJMawo1GSQJ5h98xNw4dkl4JMOSaTgjVmWLG4ph82SwW5N5r8Mzh36RjjBmOqbqXX4w_wMYf2d6G37wUXX0fRKlBISghT8vP10gENTa81WPAFjZgJxX5u6L5Vzp9uGqBZ2VJNgiFF1rUrRN8dktjtZr1bEd87hJVwORidp04MpCcgWAUGd9bxKHOwQ0YMVWSupOUZBjQ",
//            storeId,
//            dateYM
//        )
//
//        if (response.isSuccessful) {
//            return response.body()?.toData() ?: hashMapOf()
//        } else {
//            throw Exception(response.message())
//        }
        return schedules
    }

    override suspend fun requestFillIn(storeId: Int, scheduleId: Int, memberId: Int): Boolean {
        return true
    }

    override suspend fun acceptFillIn(storeId: Int, scheduleId: Int, memberId: Int): Boolean {
        return true
    }

    override suspend fun getMonthlyPossibleTimes(
        storeId: Int, dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>> {
        return possibleSchedules
    }

    override suspend fun addPossibleTime(
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        startTime: String,
        endTime: String,
    ): Int {
        return 9 //storeMemberAvailableTimeId
    }

    override suspend fun deletePossibleTime(
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String {
        return "근무 가능한 시간 정보를 삭제하였습니다."
    }

    override suspend fun changedSchedule(
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        schedules = schedules.mapValues { (dateInfo, schedules) ->
            //List<MyScheduleInfo>
            schedules.map { schedule: MyScheduleInfo ->
                if (schedule.scheduleId == scheduleUpdate.scheduleId) {
                    schedule.copy(
                        startTime = scheduleUpdate.startTime,
                        endTime = scheduleUpdate.endTime,
                        memberId = scheduleUpdate.memberId
                    )
                } else {
                    schedule
                }
            }
        } as HashMap<String, List<MyScheduleInfo>>
        return true
    }

    override suspend fun deleteSchedule(scheduleId: Int): Boolean {
        schedules = schedules.mapValues { (dateInfo, schedules) ->
            //List<MyScheduleInfo>
            schedules.filterNot { schedule: MyScheduleInfo ->
                schedule.scheduleId == scheduleId
            }
        } as HashMap<String, List<MyScheduleInfo>>
        return true
    }

    override suspend fun addSchedule(storeId: Int, scheduleUpdate: ScheduleUpdate): Boolean {
        schedules[scheduleUpdate.dateDashString] = schedules[scheduleUpdate.dateDashString]?.plus(
            MyScheduleInfo(
                scheduleId = 10,
                startTime = scheduleUpdate.startTime,
                endTime = scheduleUpdate.endTime,
                memberId = scheduleUpdate.memberId,
                workerType = "",
                workerName = "",
                isMine = false,
                isFillInNeeded = false
            )
        ) ?: emptyList()
        return true
    }
}