package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import javax.inject.Inject

class AddSchedule @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        return repository.addSchedule(storeId, scheduleUpdate)
    }
}