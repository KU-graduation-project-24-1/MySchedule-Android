package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.user.UserRepository
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateSchedule @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        scheduleEditInfo: ScheduleUpdate,
    ): Boolean {
        return repository.changeSchedule(
            accessToken,
            storeId,
            scheduleEditInfo
        )
    }
}