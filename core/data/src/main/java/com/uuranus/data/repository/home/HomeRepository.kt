package com.uuranus.data.repository.home

import com.uuranus.model.MyScheduleInfo

interface HomeRepository {
    fun getMonthlySchedules(storeId: Int, dateYM: String): HashMap<String, List<MyScheduleInfo>>

    fun requestFillIn(scheduleId: Int)

    fun acceptFillIn(scheduleId: Int)
}