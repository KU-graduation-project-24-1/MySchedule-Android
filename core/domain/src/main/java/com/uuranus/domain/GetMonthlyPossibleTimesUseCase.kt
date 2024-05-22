package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.login.LoginRepository
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import javax.inject.Inject

class GetMonthlyPossibleTimesUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyPossibleTimeInfo>> {
        return repository.getMonthlyPossibleTimes(accessToken, storeId, dateYM)
    }
}