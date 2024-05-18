package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.login.LoginRepository
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeScheduleInfo @Inject constructor(
    private val repository: HomeRepository,
) {

    suspend operator fun invoke(
        storeId: Int,
        scheduleInfo: ScheduleUpdate,
    ): Boolean {
        return repository.changeSchedule(storeId, scheduleInfo)
    }
}