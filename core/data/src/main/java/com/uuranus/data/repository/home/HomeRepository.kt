package com.uuranus.data.repository.home

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo

interface HomeRepository {
    fun getMonthlySchedules(storeId: Int, dateYM: String): HashMap<String, List<MyScheduleInfo>>

    fun requestFillIn(scheduleId: Int)

    fun acceptFillIn(scheduleId: Int)

    fun getMonthlyPossibleTimes(
        storeId: Int, dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>>
}