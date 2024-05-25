package com.uuranus.domain

import com.uuranus.data.repository.mypage.MyPageRepository
import javax.inject.Inject

class DeleteFixedPossibleTimeUseCase @Inject constructor(
    private val repository: MyPageRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        weekNum: Int,
        storeAvailableTimeByDayId: Int,
    ): Boolean {
        return repository.deleteFixedPossibleTime(
            accessToken,
            weekNum,
            storeAvailableTimeByDayId,
        )
    }
}