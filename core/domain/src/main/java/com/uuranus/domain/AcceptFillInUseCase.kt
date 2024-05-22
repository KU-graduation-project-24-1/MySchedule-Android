package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import javax.inject.Inject

class AcceptFillInUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        scheduleId: Int,
        memberId: Int,
    ): Boolean {
        return repository.acceptFillIn(
            accessToken,
            storeId,
            scheduleId,
            memberId
        )
    }
}