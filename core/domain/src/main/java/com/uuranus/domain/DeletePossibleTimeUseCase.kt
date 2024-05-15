package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import javax.inject.Inject

class DeletePossibleTimeUseCase @Inject constructor(
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