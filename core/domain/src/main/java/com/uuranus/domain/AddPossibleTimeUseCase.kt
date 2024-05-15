package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import javax.inject.Inject

class AddPossibleTimeUseCase @Inject constructor(
    private val repository: HomeRepository,
) {

    suspend operator fun invoke(
        memberId: Int,
        storeId: Int,
        dateYMD: String,
        starTime: String,
        endTime: String,
    ): Int {
        return repository.addPossibleTime(memberId, storeId, dateYMD, starTime, endTime)
    }
}