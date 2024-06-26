package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.login.LoginRepository
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMonthlyScheduleUseCase @Inject constructor(
    private val repository: HomeRepository,
) {

    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        dateYM: String,
    ): HashMap<String, List<MyScheduleInfo>> {
        return repository.getMonthlySchedules(accessToken, storeId, dateYM)
    }
}