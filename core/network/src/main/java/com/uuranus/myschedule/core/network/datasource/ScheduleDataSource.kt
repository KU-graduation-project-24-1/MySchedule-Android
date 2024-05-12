package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyScheduleInfo

interface ScheduleDataSource {

    fun getMonthlySchedules(storeId: Int, dateYM: String): HashMap<String, List<MyScheduleInfo>>

    fun requestFillIn(scheduleId: Int)

    fun acceptFillIn(scheduleId: Int)

}