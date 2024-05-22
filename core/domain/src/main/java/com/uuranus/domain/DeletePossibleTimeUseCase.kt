package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import javax.inject.Inject

class DeletePossibleTimeUseCase @Inject constructor(
    private val repository: HomeRepository,
) {

    suspend operator fun invoke(
        accessToken:String,
        memberId: Int,
        storeId: Int,
        storeMemberAvailableTimeId: Int,
    ): Boolean {
        return repository.deletePossibleTime(accessToken,memberId, storeId, storeMemberAvailableTimeId)
    }
}