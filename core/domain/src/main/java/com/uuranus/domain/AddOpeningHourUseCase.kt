package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.mypage.MyPageRepository
import javax.inject.Inject


class AddOpeningHourUseCase @Inject constructor(
    private val repository: MyPageRepository,
) {

    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        weekNum: Int,
        startTime: String,
        endTime: String,
    ): Int {
        return repository.addStoreOpeningHourTime(
            accessToken,
            storeId,
            weekNum,
            startTime,
            endTime
        )
    }
}