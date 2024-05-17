package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

private var schedules = hashMapOf(
    "2024-05-16" to listOf(
        MyScheduleInfo(
            scheduleId = 9,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 3,
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
            memberId = 3,
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
            memberId = 3,
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
            memberId = 3,
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
            memberId = 2,
            workerName = "이직원",
            workerType = "매니저",
            isMine = false,
            isFillInNeeded = false
        ),
        MyScheduleInfo(
            scheduleId = 3,
            startTime = "12:00",
            endTime = "15:00",
            memberId = 3,
            workerName = "김알바",
            workerType = "아르바이트",
            isMine = true,
            isFillInNeeded = false
        ),
        MyScheduleInfo(
            scheduleId = 4,
            startTime = "15:00",
            endTime = "23:59",
            memberId = 2,
            workerName = "이직원",
            workerType = "매니저",
            isMine = false,
            isFillInNeeded = true
        ),
        MyScheduleInfo(
            scheduleId = 2,
            startTime = "18:00",
            endTime = "23:00",
            memberId = 3,
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
//    private val service: MyScheduleService,
) : ScheduleDataSource {


    override suspend fun getMonthlySchedules(
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {

        return schedules
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

    override suspend fun getAllWorkers(storeId: Int): List<WorkerInfo> {
        return listOf(
            WorkerInfo(
                0,
                "최사장",
                "사장",
            ),
            WorkerInfo(
                1,
                "이직원",
                "매니저",
            ),
            WorkerInfo(
                2,
                "김알바",
                "아르바이트",
            ),
            WorkerInfo(
                3,
                "나알바",
                "아르바이트",
            ),

            )
    }

    override suspend fun changedSchedule(
        storeId: Int,
        scheduleInfo: ScheduleUpdate,
    ): Boolean {
        schedules = schedules.mapValues { (dateInfo, schedules) ->
            //List<MyScheduleInfo>
            schedules.map { schedule: MyScheduleInfo ->
                if (schedule.scheduleId == scheduleInfo.scheduleId) {
                    schedule.copy(
                        startTime = scheduleInfo.startTime,
                        endTime = scheduleInfo.endTime,
                        memberId = scheduleInfo.memberId
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
                schedule.scheduleId != scheduleId
            }
        } as HashMap<String, List<MyScheduleInfo>>
        return true
    }

    override suspend fun addSchedule(scheduleInfo: MyScheduleInfo): Boolean {
        return true
    }

//    override suspend fun addSchedule(
//        storeId: Int,
//        dateDashString: String,
//        scheduleInfo: MyScheduleInfo,
//    ): Boolean {
////        schedules = schedules.plus(
////            scheduleInfo
////        )
//        return true
//    }
}