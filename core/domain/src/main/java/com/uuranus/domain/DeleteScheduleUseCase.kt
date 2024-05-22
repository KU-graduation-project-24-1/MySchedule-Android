package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import javax.inject.Inject

class DeleteScheduleUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
    ): Boolean {
        return repository.deleteSchedule(accessToken, storeId, scheduleId)
    }
}