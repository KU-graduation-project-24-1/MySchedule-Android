package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.ScheduleUpdate
import javax.inject.Inject

class AcceptFillIn @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        return repository.acceptFillIn(
            storeId,
            scheduleId,
            memberId
        )
    }
}