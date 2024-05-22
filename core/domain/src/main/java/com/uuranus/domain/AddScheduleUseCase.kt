package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.ScheduleUpdate
import javax.inject.Inject

class AddScheduleUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        scheduleUpdate: ScheduleUpdate,
    ): Boolean {
        return repository.addSchedule(accessToken, storeId, scheduleUpdate)
    }
}