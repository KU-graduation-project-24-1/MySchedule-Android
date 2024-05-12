package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class ScheduleDataSourceImpl @Inject constructor(
//    private val service: MyScheduleService,
) : ScheduleDataSource {
    override fun getMonthlySchedules(
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {

        return hashMapOf(
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
    }

    override fun requestFillIn(scheduleId: Int) {
        TODO("Not yet implemented")
    }

    override fun acceptFillIn(scheduleId: Int) {
        TODO("Not yet implemented")
    }
}

//val dummyDate = HashMap<DateInfo, ScheduleInfo<MyScheduleInfo>>().apply {
//    put(
//        DateInfo.create(2024, 5, 5),
//        ScheduleInfo(
//            false,
//            listOf(
//                ScheduleData(
//                    "AAA 10:00",
//                    MyScheduleTheme.colors.calendarBlue,
//                    detail = MyScheduleInfo(
//                        0,
//                        "10:00",
//                        "12:00",
//                        "AAA",
//                        "매니저",
//                        false,
//                        MyScheduleTheme.colors.calendarBlue,
//                        true
//                    )
//                ),
//                ScheduleData(
//                    "BBB 12:00",
//                    MyScheduleTheme.colors.calendarOrange,
//                    MyScheduleInfo(
//                        1,
//                        "12:00",
//                        "15:00",
//                        "BBB",
//                        "아르바이트",
//                        true,
//                        MyScheduleTheme.colors.calendarOrange,
//                        false
//                    ),
//                ),
//                ScheduleData(
//                    "KKK 15:00",
//                    MyScheduleTheme.colors.calendarPurple,
//                    MyScheduleInfo(
//                        2,
//                        "15:00",
//                        "18:00",
//                        "KKK",
//                        "사장",
//                        false,
//                        MyScheduleTheme.colors.calendarPurple,
//                        false
//                    ),
//                )
//            )
//        )
//    )
//    put(
//        DateInfo.create(2024, 5, 14),
//        ScheduleInfo(
//            true,
//            listOf(
//                ScheduleData(
//                    "AAA 10:00",
//                    MyScheduleTheme.colors.calendarBlue,
//                    MyScheduleInfo(
//                        0,
//                        "10:00",
//                        "12:00",
//                        "AAA",
//                        "매니저",
//                        false,
//                        MyScheduleTheme.colors.calendarBlue,
//                        true
//                    ),
//                )
//            )
//        )
//    )
//}