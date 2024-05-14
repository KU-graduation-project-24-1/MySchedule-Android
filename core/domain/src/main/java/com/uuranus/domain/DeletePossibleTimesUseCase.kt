package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.MyScheduleInfo
import javax.inject.Inject

class DeletePossibleTimesUseCase @Inject constructor(
    private val repository: HomeRepository,
) {

    suspend operator fun invoke(
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): String {
        return repository.deletePossibleTime(memberId, storeId, storeMemberAvailableTimeId)
    }
}