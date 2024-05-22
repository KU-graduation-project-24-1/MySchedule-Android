package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.ScheduleUpdate
import javax.inject.Inject

class ChangeScheduleInfoUseCase @Inject constructor(
    private val repository: HomeRepository,
) {

    suspend operator fun invoke(
        accessToken:String,
        storeId: Int,
        scheduleInfo: ScheduleUpdate,
    ): Boolean {
        return repository.changeSchedule(accessToken,storeId, scheduleInfo)
    }
}