package com.uuranus.domain

import com.uuranus.data.repository.mypage.MyPageRepository
import com.uuranus.model.TimeRange
import javax.inject.Inject

class GetFixedPossibleTimesUseCase @Inject constructor(
    private val repository: MyPageRepository,
) {
    suspend operator fun invoke(
    ): List<List<TimeRange>> {
        return repository.getFixedPossibleTimes()
    }
}