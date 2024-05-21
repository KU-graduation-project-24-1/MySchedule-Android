package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.ScheduleUpdate
import javax.inject.Inject

class RequestFillIn @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        return repository.requestFillIn(
            accessToken,
            storeId,
            scheduleId,
            memberId
        )
    }
}