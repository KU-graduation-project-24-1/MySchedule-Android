package com.uuranus.domain

import com.uuranus.data.repository.mypage.MyPageRepository
import com.uuranus.model.TimeRange
import javax.inject.Inject

class AddFixedPossibleTimesUseCase @Inject constructor(
    private val repository: MyPageRepository,
) {
    suspend operator fun invoke(
    ): String {
        return repository.addFixedPossibleTime()
    }
}