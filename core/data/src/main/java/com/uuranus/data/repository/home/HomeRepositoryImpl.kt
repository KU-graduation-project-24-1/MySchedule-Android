package com.uuranus.data.repository.home

import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.network.datasource.ScheduleDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: ScheduleDataSource,
) : HomeRepository {
    override fun getMonthlySchedules(
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {
        return dataSource.getMonthlySchedules(storeId, dateYM)
    }

    override fun requestFillIn(scheduleId: Int) {
        TODO("Not yet implemented")
    }

    override fun acceptFillIn(scheduleId: Int) {
        TODO("Not yet implemented")
    }
}