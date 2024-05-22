package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.ScheduleUpdate
import javax.inject.Inject

class UpdateScheduleUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        scheduleEditInfo: ScheduleUpdate,
    ): Boolean {
        return repository.changeSchedule(
            accessToken,
            storeId,
            scheduleEditInfo
        )
    }
}